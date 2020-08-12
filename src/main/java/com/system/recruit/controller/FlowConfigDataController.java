package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrFlowConfig;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.FlowConfigDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:34
 */
@RestController
@RequestMapping("/FlowConfig")
@Slf4j
public class FlowConfigDataController {
    @Resource
    private FlowConfigDataService flowConfigDataService;

    @PostMapping("/getFlowConfig")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getFlowConfig(@Valid @RequestBody GetFlowConfigReq getFlowConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getFlowConfig", valiResult);
            List<HrFlowConfig> result = flowConfigDataService.getFlowConfig(getFlowConfigReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/addFlowConfig")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> addFlowConfig(@Valid @RequestBody AddFlowConfigReq addFlowConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addFlowConfig", valiResult);
            Map<String,Object> result = flowConfigDataService.addFlowConfig(addFlowConfigReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/deleteFlowConfig")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> deleteFlowConfig(@Valid @RequestBody DeleteFlowConfigReq deleteFlowConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteFlowConfig", valiResult);
            Map<String,Object> result = flowConfigDataService.deleteFlowConfig(deleteFlowConfigReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/sortFlowConfig")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> sortFlowConfig(@Valid @RequestBody SortFlowConfigReq sortFlowConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("sortFlowConfig", valiResult);
            Map<String,Object> result = flowConfigDataService.sortFlowConfig(sortFlowConfigReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

}
