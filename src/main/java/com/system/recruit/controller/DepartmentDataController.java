package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrDepartment;
import com.system.recruit.entity.info.AddDepartmentReq;
import com.system.recruit.entity.info.DeleteDepartmentReq;
import com.system.recruit.entity.info.GetDepartmentByIdReq;
import com.system.recruit.service.DepartmentDataService;
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
 * @date 2020/5/12 0012 17:52
 */
@RestController
@RequestMapping("/Department")
@Slf4j
public class DepartmentDataController {
    @Resource
    private DepartmentDataService departmentDataService;

    @PostMapping("/getAllDepartment")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getAllDepartment(HttpServletRequest request){
        List<HrDepartment> result = departmentDataService.getAllDepartment(request);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }
    @PostMapping("/getDepartmentById")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getDepartmentById(@Valid @RequestBody GetDepartmentByIdReq getDepartmentByIdReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getDepartmentById",valiResult);
            HrDepartment result = departmentDataService.getDepartmentById(getDepartmentByIdReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
    @PostMapping("/getAllDepartmentTree")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getAllDepartmentTree(HttpServletRequest request){
        List<HrDepartment> result = departmentDataService.getAllDepartmentTree(request);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }


    @PostMapping("/addDepartment")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> addDepartment(@Valid @RequestBody AddDepartmentReq addDepartmentReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addDepartment", valiResult);
            Map<String,Object> result = departmentDataService.addDepartment(addDepartmentReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/deleteDepartment")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> deleteDepartment(@Valid @RequestBody DeleteDepartmentReq deleteDepartmentReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteDepartment", valiResult);
            Map<String,Object> result = departmentDataService.deleteDepartment(deleteDepartmentReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
