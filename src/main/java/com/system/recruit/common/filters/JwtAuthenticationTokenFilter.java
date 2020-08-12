package com.system.recruit.common.filters;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.system.recruit.common.Constant;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.*;
import com.system.recruit.entity.HrUser;
import com.system.recruit.service.SelfUserDetailsService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
//import com.deceen.common.utils.JwtTokenUtil;

/**
 * @author: zzx
 * @date: 2018/10/15 17:30
 * @description: 确保在一次请求只通过一次filter，而不需要重复执行
 */
@Component
@Slf4j
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    /*@Value("${token.expirationSeconds}")
    private int expirationSeconds;*/

    /*@Value("${token.validTime}")
    private int validTime;*/

    @Autowired
    SelfUserDetailsService userDetailsService;

    @Autowired
    RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        //获取请求的ip地址
        String currentIp = AccessAddressUtil.getIpAddress(request);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring("Bearer ".length());
            String userAccount = JwtTokenUtil.parseToken(authToken, "_secret");
            String ip = CollectionUtil.getMapValue(JwtTokenUtil.getClaims(authToken), "ip");
            //校验token在redis中是否存在
            if (!redisUtil.hasKey(authToken)){
                log.info("用户：{}的token：{}在redis中不存在，拒绝访问",userAccount,authToken);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NEED_AUTHORITIES, false)));
                out.flush();
                out.close();
                return;
            }

            //进入黑名单验证
            if (redisUtil.isBlackList(authToken)) {
                log.info("用户：{}的token：{}在黑名单之中，拒绝访问",userAccount,authToken);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(JSON.toJSONString(ResultVO.result(ResultEnum.TOKEN_IS_BLACKLIST, false)));
                out.flush();
                out.close();
                return;
            }

            //判断token是否过期
            /*
             * 过期的话，从redis中读取有效时间（比如七天登录有效），再refreshToken（根据以后业务加入，现在直接refresh）
             * 同时，已过期的token加入黑名单
             */
            if (redisUtil.hasKey(authToken) && "1".equals(redisUtil.getStateByToken(authToken))) {   //判断redis是否有保存
                String expirationTime = redisUtil.hget(authToken,"expirationTime").toString();
                if (JwtTokenUtil.isExpiration(expirationTime)) {
                    //获得redis中用户的token刷新时效
                    String tokenValidTime = (String) redisUtil.getTokenValidTimeByToken(authToken);
                    String currentTime = DateUtil.getTime();
                    //这个token已作废，加入黑名单
                    log.info("{}已作废，1分钟后加入黑名单",authToken);
                    String finalAuthToken = authToken;
                    Thread thread = new Thread(new Runnable() {
                        @SneakyThrows
                        @Override
                        public void run() {
                            Thread.sleep(Long.valueOf(Constant.REDUNDANCY_TIME));
                            log.info("异步延时1分钟后执行加入黑名单操作");
                            redisUtil.addBlackList(finalAuthToken);
                        }
                    });
                    thread.start();
                    if (DateUtil.compareDate(currentTime, tokenValidTime)) {

                        //超过有效期，不予刷新
                        log.info("{}已超过有效期，不予刷新",authToken);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(JSON.toJSONString(ResultVO.result(ResultEnum.LOGIN_IS_OVERDUE, false)));
                        out.flush();
                        out.close();
                        return;
                    } else {//仍在刷新时间内，则刷新token，放入请求头中
                        String userJson = (String) redisUtil.getUserByToken(authToken);
                        HrUser user = JSONObject.parseObject(userJson,HrUser.class);
                        userAccount = user.getUserAccount();//更新username

                        ip = (String) redisUtil.getIPByToken(authToken);//更新ip

                        //获取请求的ip地址
                        Map<String, Object> map = new HashMap<>();
                        map.put("ip", ip);
                        log.info(redisUtil.getExpireTime(authToken).toString());
                        String jwtToken = JwtTokenUtil.generateToken(userAccount, redisUtil.getExpireTime(authToken), map);


                        //更新redis
                        //Integer expire = validTime * 24 * 60 * 60 * 1000;//刷新时间
                        //redisUtil.setTokenRefresh(jwtToken,selfUserDetails,ip);
                        //删除旧的token保存的redis
                        redisUtil.hset(authToken,"state","0");
                        log.info("redis一分钟后即将删除旧token：{},新token：{}已更新redis",authToken,jwtToken);
                        String finalAuthToken1 = authToken;
                        Thread deleteKeythread = new Thread(new Runnable() {
                            @SneakyThrows
                            @Override
                            public void run() {
                                Thread.sleep(Long.valueOf(Constant.REDUNDANCY_TIME));
                                log.info("异步延时1分钟后执行删除redis中Token为"+finalAuthToken1+"操作");
                                redisUtil.deleteKey(finalAuthToken1);
                            }
                        });
                        //新的token保存到redis中
                        redisUtil.setTokenRefresh(jwtToken,userJson,ip,"1",redisUtil.getExpireTime(authToken));
                        deleteKeythread.start();
                        authToken = jwtToken;//更新token，为了后面
                        /*HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(request);
                        requestWrapper.addHeader("Authorization", "Bearer " + jwtToken);
                        request = requestWrapper;*/
                        response.setHeader("Authorization", "Bearer " + jwtToken);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(JSON.toJSONString(ResultVO.result(ResultEnum.TOKEN_IS_REFRRESH,jwtToken,true)));
                        out.flush();
                        out.close();
                        return;
                    }
                }

            }

            if (userAccount != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                /*
                 * 加入对ip的验证
                 * 如果ip不正确，进入黑名单验证
                 */
                if (!StringUtil.equals(ip, currentIp)) {//地址不正确
                    log.info("用户：{}的ip地址变动，进入黑名单校验",userAccount);
                    //进入黑名单验证
                    if (redisUtil.isBlackList(authToken)) {
                        log.info("用户：{}的token：{}在黑名单之中，拒绝访问",userAccount,authToken);
                        response.setContentType("application/json;charset=utf-8");
                        PrintWriter out = response.getWriter();
                        out.write(JSON.toJSONString(ResultVO.result(ResultEnum.TOKEN_IS_BLACKLIST, false)));
                        out.flush();
                        out.close();
                        return;
                    }
                    //黑名单没有则继续，如果黑名单存在就退出后面
                }


                UserDetails userDetails = userDetailsService.loadUserByUsername(userAccount);
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
