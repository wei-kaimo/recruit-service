package com.system.recruit.service;

import com.system.recruit.entity.HrConfig;
import com.system.recruit.entity.info.*;
import com.system.recruit.entity.info.GetAllConfigResp;

import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/9 0009 15:15
 */
public interface ConfigDataService {
    GetAllConfigResp getAllConfig(GetAllConfigReq getAllConfigReq);
    Map<String,Object> addConfig(AddConfigReq addConfig);
    Map<String,Object> deleteConfig(DeleteConfigReq deleteConfigReq);
    Map<String,Object> updateConfig(UpdateConfigReq updateConfigReq);
    List<HrConfig> getConfigByType(GetConfigByTypeReq getConfigByTypeReq);

    HrConfig getConfigById(GetConfigByIdReq getAllConfigReq);

}
