package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 02:44
 */
public class ApproveHrStageDetailsReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageDetailsId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String state;

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public String getStageDetailsId() {
        return stageDetailsId;
    }

    public void setStageDetailsId(String stageDetailsId) {
        this.stageDetailsId = stageDetailsId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
