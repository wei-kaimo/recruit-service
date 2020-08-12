package com.system.recruit.service;

import com.system.recruit.entity.HrFlowConfig;
import com.system.recruit.entity.info.AddFlowConfigReq;
import com.system.recruit.entity.info.DeleteFlowConfigReq;
import com.system.recruit.entity.info.GetFlowConfigReq;
import com.system.recruit.entity.info.SortFlowConfigReq;

import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:36
 */
public interface FlowConfigDataService {
    List<HrFlowConfig> getFlowConfig(GetFlowConfigReq getFlowConfigReq);

    Map<String,Object> addFlowConfig(AddFlowConfigReq addFlowConfigReq);

    Map<String, Object> deleteFlowConfig(DeleteFlowConfigReq deleteFlowConfigReq);

    Map<String, Object> sortFlowConfig(SortFlowConfigReq sortFlowConfigReq);
}
