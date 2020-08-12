package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.HrStageDetails;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.RecruitApplyForDataService;
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
 * @date 2020/5/27 21:27
 */
@RestController
@RequestMapping("/RecruitApplyFor")
@Slf4j
public class RecruitApplyForDataController {
    @Resource
    private RecruitApplyForDataService recruitApplyForDataService;

    @PostMapping("/getRecruitApplyFor")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getRecruitApplyFor(@Valid @RequestBody GetRecruitApplyForReq getRecruitApplyForReq,
                                                  BindingResult valiResult, HttpServletRequest request) {
        try {
            ParameterValidator.validate("RecruitApplyFor", valiResult);
            GetRecruitApplyForResp result = recruitApplyForDataService.getRecruitApplyFor(getRecruitApplyForReq, request);
            return ResultVO.result(ResultEnum.SUCCESS, result, true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
        }
    }

    @PostMapping("/getHrStageDetails")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getHrStageDetails(@Valid @RequestBody GetHrStageDetailsReq getHrStageDetailsReq, BindingResult valiResult) {
        try {
            ParameterValidator.validate("RecruitApplyFor", valiResult);
            List<HrStageDetails> result = recruitApplyForDataService.getHrStageDetails(getHrStageDetailsReq);
            return ResultVO.result(ResultEnum.SUCCESS, result, true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
        }
    }

    @PostMapping("/addHrStageDetails")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> addHrStageDetails(@Valid @RequestBody AddHrStageDetailsReq addHrStageDetailsReq, BindingResult valiResult, HttpServletRequest request) {
        try {
            ParameterValidator.validate("addHrStageDetails", valiResult);
            Map<String, Object> result = recruitApplyForDataService.addHrStageDetails(addHrStageDetailsReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
        }
    }

    @PostMapping("/deleteHrStageDetails")
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> deleteHrStageDetails(@Valid @RequestBody DeleteHrStageDetailsReq deleteHrStageDetailsReq, BindingResult valiResult) {
        try {
            ParameterValidator.validate("deleteHrStageDetails", valiResult);
            Map<String, Object> result = recruitApplyForDataService.deleteHrStageDetails(deleteHrStageDetailsReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
        }
    }

    @PostMapping("/sortHrStageDetails")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> sortHrStageDetails(@Valid @RequestBody SortHrStageDetailsReq sortHrStageDetailsReq , BindingResult valiResult){
        try {
            ParameterValidator.validate("sortHrStageDetails", valiResult);
            Map<String,Object> result = recruitApplyForDataService.sortHrStageDetails(sortHrStageDetailsReq);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
    @PostMapping("/approveHrStageDetails")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> approveHrStageDetails(@Valid @RequestBody ApproveHrStageDetailsReq approveHrStageDetailsReq , BindingResult valiResult , HttpServletRequest request){
        try {
            ParameterValidator.validate("approveHrStageDetails", valiResult);
            Map<String,Object> result = recruitApplyForDataService.approveHrStageDetails(approveHrStageDetailsReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
    @PostMapping("/sendmailToCandidate")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> sendmailToCandidate(@Valid @RequestBody SendmailToCandidateReq sendmailToCandidateReq , BindingResult valiResult , HttpServletRequest request){
        try {
            ParameterValidator.validate("sendmailToCandidate", valiResult);
            Map<String,Object> result = recruitApplyForDataService.SendmailToCandidateReq(sendmailToCandidateReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/assignResume")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> assignResume(@Valid @RequestBody AssignResumeReq assignResumeReq , BindingResult valiResult , HttpServletRequest request){
        try {
            ParameterValidator.validate("assignResume", valiResult);
            Map<String,Object> result = recruitApplyForDataService.assignResume(assignResumeReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
