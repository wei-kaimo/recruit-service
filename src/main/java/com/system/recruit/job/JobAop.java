package com.system.recruit.job;

import com.system.recruit.job.inherited.TestInherited;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/7/3 17:01
 */
@Component
@Aspect
@Slf4j
public class JobAop {

    @Resource
    private RedisLockConfig redisLockConfig;

    /*//切入点
    @Pointcut("execution(* com.system.recruit.job.TestJob.test(..))")
    public void testAop() {
    }*/

    @Pointcut("@annotation(com.system.recruit.job.inherited.TestInherited)")
    public void TestInherited() {
    }

    @Around("TestInherited()")
    public void TestInheritedBefore(ProceedingJoinPoint joinPoint){
        boolean begin = false;
        MethodSignature methodSignature=(MethodSignature) joinPoint.getSignature();
        Method method=methodSignature.getMethod();
        TestInherited testInherited=method.getAnnotation(TestInherited.class);
        String lockKey = testInherited.lockKey();
        long expireStamp = testInherited.expireStamp();
        if (expireStamp == 0){
            log.info("进入无限期锁加锁逻辑");
            begin = redisLockConfig.lock(lockKey);
            log.info("加锁结果："+begin);
        }else{
            log.info("进入有限时间锁加锁逻辑，锁过期时间"+expireStamp);
            begin = redisLockConfig.lock(lockKey,expireStamp);
            log.info("加锁结果："+begin);
        }

        if (begin){
            log.info("加锁成功");
        }else {
            log.info("加锁失败");
        }

        log.info("AOP方法环绕通知开始.....");
        try {
            Object ret = null;
            // TODO: 此处为自定义验证逻辑，符合条件则继续执行，否则终止方法的执行
            if (begin) {
                // 执行方法
                ret =  joinPoint.proceed();
                log.info("AOP方法环绕通结束，定时任务已执行");
            } else {
                log.info("AOP方法环绕通结束，触发条件不满足定时任务未执行");
                return;
            }
            redisLockConfig.delete(lockKey,RedisLockConfig.SYS_CODE);
            log.info("删除锁...");
        } catch (Throwable e) {
            redisLockConfig.delete(lockKey,RedisLockConfig.SYS_CODE);
            log.info("删除锁...");
            e.printStackTrace();
        }
    }

    /*@Around("testAop()")
    public void testAopAround(ProceedingJoinPoint joinPoint){
        System.out.println("HH 方法环绕start.....");
        try {
            Object ret = null;
            // TODO: 此处为自定义验证逻辑，符合条件则继续执行，否则终止方法的执行
            if (1 == 1) {
                // 执行方法
                ret =  joinPoint.proceed();
                System.out.println("HH 方法环绕proceed，结果是 :" + ret);
            } else {
                System.out.println("HH 方法环绕proceed，不满足条件未执行");
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }*/
}
