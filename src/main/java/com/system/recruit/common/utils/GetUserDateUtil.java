package com.system.recruit.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.UserResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/18 0018 21:53
 */
@Component
@Slf4j
public class GetUserDateUtil {
    @Autowired
    private RedisUtil redisUtil;

    public UserResp getUserDateExternalUtil(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.substring("Bearer ".length());
        String userJson = (String) redisUtil.hget(authToken,"user");
        UserResp object = JSONObject.parseObject(userJson,UserResp.class);
        return object ;
    }

    public HrUser getUserDateInternalUtil(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String authToken = authHeader.substring("Bearer ".length());
        String userJson = (String) redisUtil.hget(authToken,"user");
        HrUser object = JSONObject.parseObject(userJson,HrUser.class);
        return object ;
    }
}
