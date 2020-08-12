package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/14 0014 18:03
 */
public class InitiateRecruitReqReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String planNum;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String initiatorExamine;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String needNotApproval ;

    public String getNeedNotApproval() {
        return needNotApproval;
    }

    public void setNeedNotApproval(String needNotApproval) {
        this.needNotApproval = needNotApproval;
    }

    public String getInitiatorExamine() {
        return initiatorExamine;
    }

    public void setInitiatorExamine(String initiatorExamine) {
        this.initiatorExamine = initiatorExamine;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }
}
