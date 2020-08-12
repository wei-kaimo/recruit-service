package com.system.recruit.entity.info;

import com.system.recruit.entity.HrPosition;

import java.util.Date;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/18 0018 12:58
 */
public class GetRecruitReqFlowMsgResp {

    private String initiatorId;

    private HrPosition position;

    private String planNum;

    private Date createDate;

    private Date updateDate;

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public HrPosition getPosition() {
        return position;
    }

    public void setPosition(HrPosition position) {
        this.position = position;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
