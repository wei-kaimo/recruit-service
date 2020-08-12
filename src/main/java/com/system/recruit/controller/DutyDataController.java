package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrDuty;
import com.system.recruit.entity.info.AddDutyReq;
import com.system.recruit.entity.info.DeleteDutyReq;
import com.system.recruit.entity.info.GetAllDutyByDepartmentIdReq;
import com.system.recruit.service.DutyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 17:53
 */
@RestController
@RequestMapping("/Duty")
@Slf4j
public class DutyDataController {
    @Resource
    private DutyDataService dutyDataService;

    @PostMapping("/getAllDutyByRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> getAllDutyByRole(HttpServletRequest request){
        List<HrDuty> result = dutyDataService.getAllDutyByRole(request);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/getAllDutyByDepartmentId")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getAllDutyByDepartmentId(@Valid @RequestBody GetAllDutyByDepartmentIdReq getAllDutyByDepartmentIdReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getAllDutyByDepartmentId", valiResult);
            List<HrDuty> result = dutyDataService.getAllDutyByDepartmentId(getAllDutyByDepartmentIdReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/addDuty")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> addDuty(@Valid @RequestBody AddDutyReq addAllRoleReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addDuty", valiResult);
            Map<String,Object> result = dutyDataService.addDuty(addAllRoleReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/deleteDuty")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> deleteDuty(@Valid @RequestBody DeleteDutyReq deleteDutyReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteDuty", valiResult);
            Map<String,Object> result = dutyDataService.deleteDuty(deleteDutyReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
