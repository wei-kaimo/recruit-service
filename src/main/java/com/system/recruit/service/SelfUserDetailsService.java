package com.system.recruit.service;

import com.system.recruit.common.utils.RedisUtil;
import com.system.recruit.dao.HrUserMapper;
import com.system.recruit.entity.HrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class SelfUserDetailsService implements UserDetailsService {
    @Value("${user.redis.key}")
    private String key;
    @Resource
    private HrUserMapper userMapper;

    @Resource
    private RedisUtil redisUtil;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        //log.info("进入UserDetails");
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        HrUser user = new HrUser();
        user = redisUtil.hgetHrUser(key,userId);
        //log.info(JSONObject.toJSONString(user));
        if (user == null){
            log.info("进入数据库读取用户信息流程");
            user = userMapper.getUserByUserAccount(userId);
            if (user != null){
                redisUtil.hsetHrUser(key,userId,user);
            }
        }
        //通过username查询用户

        if(user == null){
            //仍需要细化处理
            log.info("用户不存在");
            throw new UsernameNotFoundException("该用户不存在");
        }
        //String BCryptpassword = passwordEncoder.encode(user.getUserPwd());
        String BCryptpassword = user.getUserPwd();
        user.setUserPwd(BCryptpassword);
        Set authoritiesSet = new HashSet();
        GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
        authoritiesSet.add(authority);
        user.setAuthorities(authoritiesSet);
        //log.info("调用");

        return user;
    }
}
