package com.system.recruit.entity.info;

import com.system.recruit.entity.HrTicketFlowApproval;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 19:37
 */
public class GetRecruitReqFlowResp {
    private List<HrTicketFlowApproval> recruitReqRespList;
    private String count;

    public List<HrTicketFlowApproval> getRecruitReqRespList() {
        return recruitReqRespList;
    }

    public void setRecruitReqRespList(List<HrTicketFlowApproval> recruitReqRespList) {
        this.recruitReqRespList = recruitReqRespList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
