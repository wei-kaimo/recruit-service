package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrPosition;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.PositionDataService;
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
 * @date 2020/5/17 0017 18:39
 */
@RestController
@RequestMapping("/Position")
@Slf4j
public class PositionDataController {
    @Resource
    private PositionDataService positionDataService;

    @PostMapping("getAllPosition")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2','ROLE_USER_HR')")
    public Map<String,Object> getAllPosition(@Valid @RequestBody GetAllPositionReq getAllPositionReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getAllPosition", valiResult);
            List<HrPosition> result = positionDataService.getAllPosition(getAllPositionReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("getPositionById")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getPositionById(@Valid @RequestBody GetPositionByIdReq getAllPositionReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getPositionById", valiResult);
            HrPosition result = positionDataService.getPositionById(getAllPositionReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("getPositionList")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2','ROLE_USER_HR')")
    public Map<String,Object> getPositionList(@Valid @RequestBody GetPositionListReq getPositionListReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getPositionList", valiResult);
            GetPositionListResp result = positionDataService.getPositionList(getPositionListReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("addPosition")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2','ROLE_USER_HR')")
    public Map<String,Object> addPosition(@Valid @RequestBody AddPositionReq addPositionReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("addPosition", valiResult);
            Map<String,Object> result = positionDataService.addPosition(addPositionReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("updatePosition")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2','ROLE_USER_HR')")
    public Map<String,Object> updatePosition(@Valid @RequestBody UpdatePositionReq updatePositionReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("updatePosition", valiResult);
            Map<String,Object> result = positionDataService.updatePosition(updatePositionReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("deletePosition")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BOSS_0','ROLE_BOSS_1','ROLE_BOSS_2','ROLE_BOSS_3','ROLE_BOSS_HR1','ROLE_BOSS_HR2','ROLE_USER_HR')")
    public Map<String,Object> deletePosition(@Valid @RequestBody DeletePositionReq deletePositionReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("deletePosition", valiResult);
            Map<String,Object> result = positionDataService.deletePosition(deletePositionReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
