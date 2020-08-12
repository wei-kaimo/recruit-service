package com.system.recruit.entity.info;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 18:49
 */
public class GetRecruitReqResp {
    private List<RecruitReqResp> recruitReqRespList;
    private String count;

    public List<RecruitReqResp> getRecruitReqRespList() {
        return recruitReqRespList;
    }

    public void setRecruitReqRespList(List<RecruitReqResp> recruitReqRespList) {
        this.recruitReqRespList = recruitReqRespList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
