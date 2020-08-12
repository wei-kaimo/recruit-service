package com.system.recruit.common.security;

import com.alibaba.fastjson.JSON;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: zzx
 * @date: 2018/10/15 15:04
 * @description: 用户未登录时返回给前端的数据
 */
@Component
@Slf4j
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
    /*@Autowired
    private RedisUtil redisUtil;*/
    /*@Value("${token.expirationSeconds}")
    private int expirationSeconds;*/

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {

        log.info("用户未登录处理器：AjaxAuthenticationEntryPoin触发");
        log.info("用户未登录处理器：AjaxAuthenticationEntryPoint发生反应");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        PrintWriter out = httpServletResponse.getWriter();
        out.write(JSON.toJSONString(ResultVO.result(ResultEnum.USER_NEED_AUTHORITIES,false)));
        out.flush();
        out.close();
    }
}
