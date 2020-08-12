package com.system.recruit.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.system.recruit.entity.HrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author: zzx
 * @date: 2018/10/23 10:31
 * @description: redis工具类
 */
@Component
@Slf4j
public class RedisUtil {

    @Value("${token.expirationSeconds}")
    private int expirationSeconds;

    /*常量，各种实现方式都行，这里读取application.yml*/
    /*@Value("${token.validTime}")
    private int validTime;*/

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 查询key,支持模糊查询
     *
     * @param key 传过来时key的前后端已经加入了*，或者根据具体处理
     * */
    public Set<String> keys(String key){
        return redisTemplate.keys(key);
    }

    /**
     * 字符串获取值
     * @param key
     * */
    public Object get(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 字符串存入值
     * 默认过期时间为2小时
     * @param key
     * */
    public void set(String key, String value){
        redisTemplate.opsForValue().set(key,value, 7200,TimeUnit.SECONDS);
    }

    /**
     * 字符串存入值
     * @param expire 过期时间（毫秒计）
     * @param key
     * */
    public void set(String key, String value,Integer expire){
        redisTemplate.opsForValue().set(key,value, expire,TimeUnit.SECONDS);
    }

    /**
     * 删出key
     * 这里跟下边deleteKey（）最底层实现都是一样的，应该可以通用
     * @param key
     * */
    public void delete(String key){
        redisTemplate.opsForValue().getOperations().delete(key);
    }

    /**
     * 添加单个
     * 默认过期时间为两小时
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     */
    public void hset(String key,String filed,Object domain){
        redisTemplate.opsForHash().put(key, filed, domain);
    }

    /**
     * 添加单个HrUser
     * @param key    key
     * @param UserAccount  filed
     * @param hrUser 对象
     */
    public void hsetHrUser(String key, String UserAccount, HrUser hrUser){
        String heuserJson = JSONObject.toJSONString(hrUser);
        redisTemplate.opsForHash().put(key, UserAccount, heuserJson);
    }



    /**
     * 添加单个
     * @param key    key
     * @param filed  filed
     * @param domain 对象
     * @param expire 过期时间（毫秒计）
     */
    public void hset(String key,String filed,Object domain,Integer expire){
        redisTemplate.opsForHash().put(key, filed, domain);
        redisTemplate.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * 添加HashMap
     *
     * @param key    key
     * @param hm    要存入的hash表
     */
    public void hset(String key, HashMap<String,Object> hm){
        redisTemplate.opsForHash().putAll(key,hm);
    }

    /**
     * 如果key存在就不覆盖
     * @param key
     * @param filed
     * @param domain
     */
    public void hsetAbsent(String key,String filed,Object domain){
        redisTemplate.opsForHash().putIfAbsent(key, filed, domain);
    }

    /**
     * 查询key和field所确定的值
     *
     * @param key 查询的key
     * @param field 查询的field
     * @return HV
     */
    public Object hget(String key,String field) {
        return redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 查询key和field所确定的值
     *
     * @param key 查询的key
     * @param field 查询的field
     * @return HV
     */
    public HrUser hgetHrUser(String key,String field) {
        //log.info(key,field);
        HrUser hrUser = null;
        Object object = redisTemplate.opsForHash().get(key, field);
        //log.info(object.toString());
        if (object!= null && !"".equals(object)){
            String objJson = object.toString();
            hrUser = JSONObject.parseObject( objJson,HrUser.class);
        }
        return hrUser ;
    }

    /**
     * 查询该key下所有值
     *
     * @param key 查询的key
     * @return Map<HK, HV>
     */
    public Object hget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 删除key下所有值
     *
     * @param key 查询的key
     */
    public void deleteKey(String key) {
        redisTemplate.opsForHash().getOperations().delete(key);
    }

    /**
     * 判断key和field下是否有值
     *
     * @param key 判断的key
     * @param field 判断的field
     */
    public Boolean hasKey(String key,String field) {
        return redisTemplate.opsForHash().hasKey(key,field);
    }

    /**
     * 获取key的过期时间
     *
     * @param key 判断的key
     */
    public Integer getExpireTime(String key) {
        return redisTemplate.getExpire(key).intValue() ;
    }

    /**
     * 判断key下是否有值
     *
     * @param key 判断的key
     */
    public Boolean hasKey(String key) {
        return redisTemplate.opsForHash().getOperations().hasKey(key);
    }

    /**
     * 判断此token是否在黑名单中
     * @param token
     * @return
     */
    public Boolean isBlackList(String token){
        return hasKey("blacklist",token);
    }

    /**
     * 将token加入到redis黑名单中
     * @param token
     */
    public void addBlackList(String token){
        hset("blacklist", token,"true");
    }


    /**
     * 查询token下的刷新时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getTokenValidTimeByToken(String token) {
        return redisTemplate.opsForHash().get(token, "tokenValidTime");
    }

    /**
     * @param token 查询的key
     * @return HV
     */
    public Object getUserByToken(String token) {
        return redisTemplate.opsForHash().get(token, "user");
    }

    /**
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getIPByToken(String token) {
        return redisTemplate.opsForHash().get(token, "ip");
    }

    public String getStateByToken(String token){
        String state = (String) redisTemplate.opsForHash().get(token, "state");
        return state;
    }

    /**
     * 查询token下的过期时间
     *
     * @param token 查询的key
     * @return HV
     */
    public Object getExpirationTimeByToken(String token) {
        return redisTemplate.opsForHash().get(token, "expirationTime");
    }

    public void setTokenRefresh(String token, String suerJson, String ip ,String state , Integer expire){
        //刷新时间
        //Integer expire = validTime*24*60*60*1000;

        hset(token, "tokenValidTime",DateUtil.getAddDayMsec(expire),expire);
        hset(token, "expirationTime",DateUtil.getAddDaySecond(expirationSeconds),expire);
        hset(token, "user",suerJson,expire);
        hset(token, "ip",ip,expire);
        hset(token,"state",state,expire);
    }
}
