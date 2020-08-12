package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrMenu;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.AddMenuReq;
import com.system.recruit.entity.info.DeleteMenuReq;
import com.system.recruit.entity.info.SortMenuReq;
import com.system.recruit.entity.info.UpdateMenuReq;
import com.system.recruit.service.MenuDataService;
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
 * @date 2020/5/7 0007 15:17
 */
@RestController
@RequestMapping("/Menu")
@Slf4j
public class MenuDataController {

    @Resource
    private MenuDataService menuDataService;
    @Resource
    private GetUserDateUtil getUserDateUtil;

    @PostMapping("/getTreeMenuMenu")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getTreeMenuMenu(HttpServletRequest request){
        HrUser user = getUserDateUtil.getUserDateInternalUtil(request);
        List<HrMenu> result = menuDataService.getMenuByRole(user);
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/getAllMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> getAllMenu(){
        List<HrMenu> result = menuDataService.getAllRole();
        return ResultVO.result(ResultEnum.SUCCESS,result,true);
    }

    @PostMapping("/addMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> addMenu(@Valid @RequestBody AddMenuReq addMenuReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addMenu", valiResult);
            Map<String,Object> result = menuDataService.addMenu(addMenuReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/deleteMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> deleteMenu(@Valid @RequestBody DeleteMenuReq deleteMenuReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deleteMenu", valiResult);
            Map<String,Object> result = menuDataService.deleteMenu(deleteMenuReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/updateMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> updateMenu(@Valid @RequestBody UpdateMenuReq updateMenuReq, BindingResult valiResult){
        try {
            ParameterValidator.validate("updateMenu", valiResult);
            Map<String,Object> result = menuDataService.updateMenu(updateMenuReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/sortMenu")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Map<String,Object> sortMenu(@Valid @RequestBody SortMenuReq sortMenuReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("sortMenu", valiResult);
            Map<String,Object> result = menuDataService.sortMenu(sortMenuReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

}
