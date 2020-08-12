package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 21:26
 */
public class AddFlowConfigReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String operationType;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }
}
