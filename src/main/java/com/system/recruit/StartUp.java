package com.system.recruit;

import com.system.recruit.common.Constant;
import com.system.recruit.common.utils.RedisUtil;
import com.system.recruit.dao.HrSysConfigMapper;
import com.system.recruit.dao.HrUserMapper;
import com.system.recruit.entity.HrSysConfig;
import com.system.recruit.entity.HrUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


/**
 * 服务启动执行，加载配置问题
 */
@Component
@Slf4j
public class StartUp implements CommandLineRunner {
    @Value("${user.redis.key}")
    private String key;
    @Resource
    private HrUserMapper hrUserMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private HrSysConfigMapper hrSysConfigMapper;

    @Override
    public void run(String... strings) throws Exception {
        log.info("加载用户信息到redis缓存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<HrUser> hrUsers = hrUserMapper.getAllUser();
        int i = 0;
        for (HrUser hrUser : hrUsers) {
            redisUtil.hsetHrUser(key,hrUser.getUserAccount(),hrUser);
            i++;
        }
        log.info("公加载用户信息"+i+"条到redis缓存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        uploadSysConfig();
    }

    private void uploadSysConfig(){
        log.info("加载系统配置到本机内存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<HrSysConfig> sysConfigs = hrSysConfigMapper.getAllByPrimaryKey();
        int i = 0;
        for (HrSysConfig config : sysConfigs) {
            Constant.SYS_CONFIG.put(config.getSysConfigName(),config.getSysConfigContent());
            i++;
        }
        log.info("加载系统配置"+i+"条到本机内存成功>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    public void refreshUserMsg(){
        log.info("刷新用户信息到缓存>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        List<HrUser> hrUsers = hrUserMapper.getAllUser();
        int i = 0;
        for (HrUser hrUser : hrUsers) {
            redisUtil.hsetHrUser(key,hrUser.getUserAccount(),hrUser);
            i++;
        }
        log.info("刷新用户用户信息"+i+"条>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
