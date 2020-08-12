package com.system.recruit.controller;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.ResumeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 16:57
 */
@RestController
@RequestMapping("/Resume")
@Slf4j
public class ResumeDataController {

    @Resource
    private ResumeDataService resumeDataService;

    @PostMapping("/getResumeByCondition")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getResumeByCondition(@Valid @RequestBody GetResumeByConditionReq getResumeByConditionReq , BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("getResumeByCondition", valiResult);
            GetResumeByConditionResp result = resumeDataService.getResumeByCondition(getResumeByConditionReq,request);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/correctResume")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> correctResume(@Valid @RequestBody CorrectResumeReq correctResumeReq, BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("correctResume", valiResult);
            Map<String,Object> result = resumeDataService.correctResumeByPosition(correctResumeReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/getCorrectResumeByCondition")
    @PreAuthorize("isAuthenticated()")
    public Map<String,Object> getCorrectResumeByCondition(@Valid @RequestBody GetCorrectResumeByConditionReq getCorrectResumeByConditionReq, BindingResult valiResult, HttpServletRequest request){
        try {
            ParameterValidator.validate("getCorrectResumeByCondition", valiResult);
            GetCorrectResumeByConditionResp result = resumeDataService.getCorrectResumeByCondition(getCorrectResumeByConditionReq,request);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/uploadResume")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> uploadResume(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        Map<String,Object> result = resumeDataService.uploadResume(file,request);
        return result;
    }

    @PostMapping("/getUploadContent")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getUploadContent(@Valid @RequestBody GetUploadContentReq getUploadContentReq, BindingResult valiResult, HttpServletRequest request) {
        try {
            ParameterValidator.validate("getUploadContent", valiResult);
            Map<String,Object> result = resumeDataService.getUploadContent(getUploadContentReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }

    @PostMapping("/addResume")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> addResume(@Valid @RequestBody AddResumeReq resumeReq, BindingResult valiResult, HttpServletRequest request) {
        try {
            ParameterValidator.validate("addResume", valiResult);
            Map<String,Object> result = resumeDataService.addResume(resumeReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }


    @PostMapping("/getResume")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> getResume(@Valid @RequestBody GetResumeReq getResumeReq, BindingResult valiResult, HttpServletRequest request) {
        try {
            ParameterValidator.validate("getResume", valiResult);
            GetResumeResp result = resumeDataService.getResume(getResumeReq,request);
            return ResultVO.result(ResultEnum.SUCCESS,result,true);
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }


}
