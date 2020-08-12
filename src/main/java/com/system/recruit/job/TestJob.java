package com.system.recruit.job;

import com.system.recruit.job.inherited.TestInherited;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/7/3 15:54
 */
@Configuration
@EnableScheduling
@Slf4j
public class TestJob {

    @TestInherited(lockKey = "test")
    @Scheduled(cron = "10/2 * * * * ?")
    public void test() throws InterruptedException {
        for (int i = 0; i <10; i++) {
            Thread.sleep(1000);
            log.info("定时任务运行中："+(i+1));
        }
    }
}
