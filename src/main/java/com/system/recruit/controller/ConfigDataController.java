package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrConfig;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.ConfigDataService;
import com.system.recruit.entity.info.GetAllConfigResp;
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
 * @date 2020/5/9 0009 15:13
 */
@RestController
@RequestMapping("/Config")
@Slf4j
public class ConfigDataController {
    @Resource
    private ConfigDataService configDataService;

    @PostMapping("getAllConfig")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> getAllConfig(@Valid @RequestBody GetAllConfigReq getAllConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getAllConfig", valiResult);
            GetAllConfigResp result = configDataService.getAllConfig(getAllConfigReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("getConfigById")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getConfigById(@Valid @RequestBody GetConfigByIdReq getAllConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getConfigById", valiResult);
            HrConfig result = configDataService.getConfigById(getAllConfigReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("addConfig")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> addConfig(@Valid @RequestBody AddConfigReq addConfig , BindingResult valiResult){
        try {
            ParameterValidator.validate("addConfig", valiResult);
            Map<String,Object> result = configDataService.addConfig(addConfig);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("deleteConfig")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> deleteConfig(@Valid @RequestBody DeleteConfigReq deleteConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteConfig", valiResult);
            Map<String,Object> result = configDataService.deleteConfig(deleteConfigReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("updateConfig")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> updateConfig(@Valid @RequestBody UpdateConfigReq updateConfigReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("updateConfig", valiResult);
            Map<String,Object> result = configDataService.updateConfig(updateConfigReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("getConfigByType")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getConfigByType( @Valid @RequestBody GetConfigByTypeReq getConfigByTypeReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getConfigByType", valiResult);
            List<HrConfig> result = configDataService.getConfigByType(getConfigByTypeReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

}
