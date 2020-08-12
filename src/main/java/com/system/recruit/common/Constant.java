package com.system.recruit.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/26 0026 14:34
 */
@Component
@PropertySource(value="classpath:application.properties",encoding = "utf-8")
public class Constant {
    public static String REDUNDANCY_TIME;
    public static String RSA_ENCRYPTED_PRIVATE_KEY;
    public static final Map<String,String> SYS_CONFIG = new HashMap<>();
    public static String ACTiVE;


    @Autowired(required = true)
    public void setRedundancyTime( @Value("${token.redundancy}") String redundancyTime) {
        REDUNDANCY_TIME = redundancyTime;
    }

    @Autowired(required = true)
    public  void setRsaEncryptedPrivateKey(@Value("${rsa.encrypted.private.key}") String rsaEncryptedPrivateKey) {
        RSA_ENCRYPTED_PRIVATE_KEY = rsaEncryptedPrivateKey;
    }

    @Autowired(required = true)
    public void setACTiVE( @Value("${spring.profiles.active}") String ACTiVE) {
        Constant.ACTiVE = ACTiVE;
    }
}
