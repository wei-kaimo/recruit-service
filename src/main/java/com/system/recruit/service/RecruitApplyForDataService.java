package com.system.recruit.service;

import com.system.recruit.entity.HrRecruitRequest;
import com.system.recruit.entity.HrStageDetails;
import com.system.recruit.entity.info.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/28 12:34
 */
public interface RecruitApplyForDataService {

    GetRecruitApplyForResp getRecruitApplyFor(GetRecruitApplyForReq getRecruitApplyForReq, HttpServletRequest request);

    List<HrStageDetails> getHrStageDetails(GetHrStageDetailsReq getHrStageDetailsReq );

    void generateRecruitApplyFor(HrRecruitRequest hrRecruitRequest );

    Map<String, Object> deleteHrStageDetails(DeleteHrStageDetailsReq deleteHrStageDetailsReq);

    Map<String, Object> addHrStageDetails(AddHrStageDetailsReq addHrStageDetailsReq,HttpServletRequest request);

    Map<String, Object> sortHrStageDetails(SortHrStageDetailsReq sortHrStageDetailsReq);

    Map<String, Object> approveHrStageDetails(ApproveHrStageDetailsReq approveHrStageDetailsReq, HttpServletRequest request);

    Map<String, Object> SendmailToCandidateReq(SendmailToCandidateReq sendmailToCandidateReq, HttpServletRequest request);

    Map<String, Object> assignResume(AssignResumeReq assignResumeReq, HttpServletRequest request);
}
