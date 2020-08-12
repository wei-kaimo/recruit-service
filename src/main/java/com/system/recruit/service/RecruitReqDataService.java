package com.system.recruit.service;

import com.system.recruit.entity.info.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/14 0014 15:21
 */
public interface RecruitReqDataService {
    Map<String, Object> initiateRecruitReq(InitiateRecruitReqReq initiateRecruitReqReq, HttpServletRequest request);

    GetRecruitReqResp getRecruitReq(GetRecruitReqReq getRecruitReqReq, HttpServletRequest request);

    GetRecruitReqFlowResp getRecruitReqFlow(GetRecruitReqFlowReq getRecruitReqFlowReq, HttpServletRequest request);

    Map<String, Object> approvalRecruitReqFlow(ApprovalRecruitReqFlowReq approvalRecruitReqFlowReq, HttpServletRequest request);

    GetRecruitReqFlowMsgResp getRecruitReqFlowMsg(GetRecruitReqFlowMsgReq getRecruitReqFlowMsgReq);

    Map<String, Object> closeRecruitReq(CloseRecruitReqReq closeRecruitReqReq, HttpServletRequest request);
}

