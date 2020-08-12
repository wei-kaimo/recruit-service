package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrRole;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.RoleDataService;
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
 * @date 2020/5/12 0012 17:51
 */

@RestController
@RequestMapping("/Role")
@Slf4j
public class RoleDataController {
    @Resource
    private RoleDataService roleDataService;

    @PostMapping("/getRole")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getRole(){
        List<HrRole> result = roleDataService.getRole();
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/getAllRole")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getAllRole(@Valid @RequestBody GetAllRoleReq getAllRoleReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getAllRole", valiResult);
            GetAllRoleResp result = roleDataService.getAllRole(getAllRoleReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/setRoleMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> setRoleMenu(@Valid @RequestBody SetRoleMenuReq setRoleMenuReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("setRoleMenu", valiResult);
            Map<String,Object> result = roleDataService.setRoleMenu(setRoleMenuReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getRoleMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> getRoleMenu(@Valid @RequestBody GetRoleMenuReq getRoleMenuReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getRoleMenu", valiResult);
            List<GetRoleMenuResp> result = roleDataService.getRoleMenu(getRoleMenuReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/addAllRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> addAllRole(@Valid @RequestBody AddAllRoleReq addAllRoleReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addAllRole", valiResult);
            Map<String,Object> result = roleDataService.addAllRole(addAllRoleReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
    @PostMapping("/deleteRole")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> deleteRole(@Valid @RequestBody DeleteRole deleteRole, BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteRole", valiResult);
            Map<String,Object> result = roleDataService.deleteRole(deleteRole);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
