package com.system.recruit.job.inherited;

import java.lang.annotation.*;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/7/3 18:14
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestInherited {
    String lockKey() default "sysLock";
    long expireStamp() default 0;

}
