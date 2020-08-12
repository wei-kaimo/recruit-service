package com.system.recruit.controller;


import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.config.ServiceException;
import com.system.recruit.common.utils.ParameterValidator;
import com.system.recruit.entity.info.SendInterviewEmailReq;
import com.system.recruit.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/Mail")
@Slf4j
public class MailController {
    @Resource
    private MailService mailService;
    @PostMapping("/sendInterviewEmail")
    //@PreAuthorize("isAuthenticated()")
    public Map<String,Object> sendInterviewEmail(@Valid @RequestBody SendInterviewEmailReq sendInterviewEmailReq , HttpServletRequest request , BindingResult valiResult) {
        try {
            ParameterValidator.validate("sendInterviewEmail", valiResult);
            Map<String,Object> result = mailService.sendInterviewEmail(sendInterviewEmailReq,request);
            return result;
        } catch (ServiceException e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(),e.getMessage());
        }
    }
}
