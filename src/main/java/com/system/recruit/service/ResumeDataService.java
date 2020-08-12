package com.system.recruit.service;

import com.system.recruit.entity.info.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 17:15
 */
public interface ResumeDataService {
    GetResumeByConditionResp getResumeByCondition(GetResumeByConditionReq getResumeByConditionReq, HttpServletRequest request);

    Map<String, Object> correctResumeByPosition(CorrectResumeReq correctResumeReq, HttpServletRequest request);

    GetCorrectResumeByConditionResp getCorrectResumeByCondition(GetCorrectResumeByConditionReq getCorrectResumeByConditionReq, HttpServletRequest request);

    Map<String, Object> uploadResume(MultipartFile file, HttpServletRequest request);

    Map<String, Object> getUploadContent(GetUploadContentReq getUploadContentReq, HttpServletRequest request);

    Map<String, Object> addResume(AddResumeReq resumeReq, HttpServletRequest request);

    GetResumeResp getResume(GetResumeReq getResumeReq, HttpServletRequest request);
}
