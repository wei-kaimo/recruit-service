package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.RecruitReqDataService;
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
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/14 0014 15:20
 */
@RestController
@RequestMapping("/RecruitReq")
@Slf4j
public class RecruitReqDataController {
    @Resource
    private RecruitReqDataService recruitReqDataService;

    @PostMapping("/initiateRecruitReq")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> initiateRecruitReq(@Valid @RequestBody InitiateRecruitReqReq initiateRecruitReqReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("initiateRecruitReq", valiResult);
            Map<String,Object> result = recruitReqDataService.initiateRecruitReq(initiateRecruitReqReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getRecruitReq")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getRecruitReq(@Valid @RequestBody GetRecruitReqReq getRecruitReqReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("getRecruitReq", valiResult);
            GetRecruitReqResp result = recruitReqDataService.getRecruitReq(getRecruitReqReq,request);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
    @PostMapping("/getRecruitReqFlow")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getRecruitReqFlow(@Valid @RequestBody GetRecruitReqFlowReq getRecruitReqFlowReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("getRecruitReqFlow", valiResult);
            GetRecruitReqFlowResp result = recruitReqDataService.getRecruitReqFlow(getRecruitReqFlowReq,request);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getRecruitReqFlowMsg")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getRecruitReqFlowMsg(@Valid @RequestBody GetRecruitReqFlowMsgReq getRecruitReqFlowMsgReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("getRecruitReqFlowMsg", valiResult);
            GetRecruitReqFlowMsgResp result = recruitReqDataService.getRecruitReqFlowMsg(getRecruitReqFlowMsgReq);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/approvalRecruitReqFlow")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> approvalRecruitReqFlow(@Valid @RequestBody ApprovalRecruitReqFlowReq approvalRecruitReqFlowReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("approvalRecruitReqFlow", valiResult);
            if ("2".equals(approvalRecruitReqFlowReq.getTicketType()) && ( approvalRecruitReqFlowReq.getProcessorId() == null || "".equals(approvalRecruitReqFlowReq.getProcessorId()))){
                return ResultVO.failure(ResultEnum.FAILURE.getCode(),"接口approvalRecruitReqFlow的入参processorId不能为空");
            }
            Map<String,Object> result = recruitReqDataService.approvalRecruitReqFlow(approvalRecruitReqFlowReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/closeRecruitReq")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> closeRecruitReq(@Valid @RequestBody CloseRecruitReqReq closeRecruitReqReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("closeRecruitReq", valiResult);
            if (!"11".equals(closeRecruitReqReq.getState()) && !"20".equals(closeRecruitReqReq.getState())){
                return ResultVO.failure(ResultEnum.FAILURE.getCode(),"state取之仅可为11或20");
            }
            Map<String,Object> result = recruitReqDataService.closeRecruitReq(closeRecruitReqReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
