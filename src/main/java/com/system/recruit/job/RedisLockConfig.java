package com.system.recruit.job;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/7/3 15:57
 */

@Component
@Slf4j
public class RedisLockConfig {
    public static final String LOCK_PREFIX = "redis_lock";
    public static final String SYS_CODE = UUID.randomUUID().toString();
    public static final long LOCK_EXPIRE = 300000; // ms

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *  分布式  加锁
     *
     * @param key key值
     * @param expireStamp 超时时间戳(毫秒) 如果为空使用默认时间
     * @return 是否获取到
     */
    public boolean lock(String key,long expireStamp){
         String timeStamp =Long.toString(new Date().getTime()+expireStamp) ;
        Map<String,String> map = new HashMap<>();
        map.put("timeStamp",timeStamp);
        map.put("sysCode",SYS_CODE);
        if(redisTemplate.opsForValue().setIfAbsent(key,map)){
            // 对应setnx命令，可以成功设置,也就是key不存在
            return true;
        }

        // 判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        Map<String,String> val = (Map<String, String>) redisTemplate.opsForValue().get(key);
        String currentLock = val.get("timeStamp");
        // 如果锁过期 currentLock不为空且小于当前时间
        if(StringUtils.isNotBlank(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            // 获取上一个锁的时间value 对应getset，如果lock存在
            Map<String,String> preLockMap = (Map<String, String>)redisTemplate.opsForValue().getAndSet(key,map);
            String preLock = preLockMap.get("timeStamp");

            // 假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentLock=A(get取的旧的值肯定是一样的),两个线程的timeStamp都是B,key都是K.锁时间已经过期了。
            // 而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的timeStamp已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(StringUtils.isNotBlank(preLock) && preLock.equals(currentLock) ){
                // preLock不为空且preLock等于currentLock，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }

    /**
     *  分布式  加锁
     *
     * @param key key值
     * @return 是否获取到
     */
    public boolean lock(String key){
        String timeStamp =Long.toString(new Date().getTime()+LOCK_EXPIRE) ;
        Map<String,String> map = new HashMap<>();
        map.put("timeStamp",timeStamp);
        map.put("sysCode",SYS_CODE);
        if(redisTemplate.opsForValue().setIfAbsent(key,map)){
            log.info("对应setnx命令，可以成功设置,也就是key不存在");
            // 对应setnx命令，可以成功设置,也就是key不存在
            return true;
        }

        // 判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁
        log.info("判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁");
        Map<String,String> val = (Map<String, String>) redisTemplate.opsForValue().get(key);
        if (val==null){
            return lock(key);
        }
        String currentLock = val.get("timeStamp");
        log.info("判断锁超时 - 防止原来的操作异常，没有运行解锁操作  防止死锁:"+currentLock);
        // 如果锁过期 currentLock不为空且小于当前时间
        if(StringUtils.isNotBlank(currentLock) && Long.parseLong(currentLock) < System.currentTimeMillis()){
            log.info("锁过期逻辑触发");
            // 获取上一个锁的时间value 对应getset，如果lock存在
            Map<String,String> preLockMap = (Map<String, String>)redisTemplate.opsForValue().getAndSet(key,map);
            String preLock = preLockMap.get("timeStamp");
            log.info("上个锁的vle："+preLock);
            // 假设两个线程同时进来这里，因为key被占用了，而且锁过期了。获取的值currentLock=A(get取的旧的值肯定是一样的),两个线程的timeStamp都是B,key都是K.锁时间已经过期了。
            // 而这里面的getAndSet一次只会一个执行，也就是一个执行之后，上一个的timeStamp已经变成了B。只有一个线程获取的上一个值会是A，另一个线程拿到的值是B。
            if(StringUtils.isNotBlank(preLock) && preLock.equals(currentLock) ){
                log.info("preLock不为空且preLock等于currentLock，也就是校验是不是上个对应的商品时间戳，也是防止并发");
                // preLock不为空且preLock等于currentLock，也就是校验是不是上个对应的商品时间戳，也是防止并发
                return true;
            }
        }
        return false;
    }

    /**
     * 删除锁
     *
     * @param key
     */
    public void delete(String key,String sysCode) {
        Map<String,String> map = (Map<String,String>) redisTemplate.opsForValue().get(key);
        if (sysCode.equals(map.get("sysCode"))){
            log.info("可以删除");
            boolean flag = redisTemplate.delete(key);
            log.info("锁删除结果："+flag);
        }else{
            log.info("其它资源未释放锁");
        }
    }

}
