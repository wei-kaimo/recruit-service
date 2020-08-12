package com.system.recruit.controller;


import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.UserOutline;
import com.system.recruit.entity.UserParticulars;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.UserDataService;
import com.system.recruit.entity.info.GetAllUserReq;
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

@RestController
@RequestMapping("/User")
@Slf4j
public class UserDataController {

    @Resource
    private UserDataService userDataService;


    @PostMapping("/getDepartmentUser")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getDepartmentUser(HttpServletRequest request){
        List<UserOutline> result = userDataService.getDepartmentUser(request);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/getCurrentUser")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getCurrentUser(HttpServletRequest request){
        UserResp result = userDataService.getCurrentUser(request);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/updateCurrentUser")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> AOPUpdateCurrentUser(@Valid @RequestBody UpdateCurrentUserReq updateCurrentUserReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("updateCurrentUser", valiResult);
            Map<String,Object> result = userDataService.updateCurrentUser(updateCurrentUserReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getUserByRoleId")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getUserByRoleId(@Valid @RequestBody GetUserByRoleIdReq getUserByRoleIdReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getUserByRoleId", valiResult);
            List<UserResp> result = userDataService.getUserByRoleId(getUserByRoleIdReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getAllUser")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> getAllUser(@Valid @RequestBody GetAllUserReq getAllUserReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getAllUser", valiResult);
            GetAllUserResp result = userDataService.getAllUser(getAllUserReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getUserParticulars")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getUserParticulars(@Valid @RequestBody GetUserParticularsReq getUserParticularsReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getUserParticulars", valiResult);
            UserParticulars result = userDataService.getUserParticulars(getUserParticularsReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/updateUserParticulars")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> AOPUpdateUserParticulars(@Valid @RequestBody UpdateUserParticularsReq updateUserParticularsReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("updateUserParticulars", valiResult);
            Map<String,Object> result = userDataService.updateUserParticulars(updateUserParticularsReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/deleteUserParticulars")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> AOPDeleteUserParticulars(@Valid @RequestBody DeleteUserParticularsReq deleteUserParticularsReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteUserParticulars", valiResult);
            Map<String,Object> result = userDataService.deleteUserParticulars(deleteUserParticularsReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/addUserParticulars")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_HR1','ROLE_BOSS_HR2')")
    public Map<String,Object> AOPAddUserParticulars(@Valid @RequestBody AddUserParticularsReq addUserParticularsReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addUserParticulars", valiResult);
            Map<String,Object> result = userDataService.addUserParticulars(addUserParticularsReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.info(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
