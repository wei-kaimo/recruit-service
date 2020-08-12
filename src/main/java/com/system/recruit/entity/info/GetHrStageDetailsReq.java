package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 21:51
 */
public class GetHrStageDetailsReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String applyForId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getApplyForId() {
        return applyForId;
    }

    public void setApplyForId(String applyForId) {
        this.applyForId = applyForId;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }
}
