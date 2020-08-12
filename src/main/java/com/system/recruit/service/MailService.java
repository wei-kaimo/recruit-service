package com.system.recruit.service;

import com.system.recruit.entity.GetMailContent;
import com.system.recruit.entity.info.SendInterviewEmailReq;
import com.system.recruit.entity.info.SendReexamineEmailReq;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface MailService {
    boolean sendEmail(String email, String subject, GetMailContent content);
    Map<String,Object> sendInterviewEmail(SendInterviewEmailReq sendInterviewEmailReq , HttpServletRequest request);
    Map<String, Object> sendReexamineEmail(SendReexamineEmailReq sendReexamineEmailReq, HttpServletRequest request);
}
