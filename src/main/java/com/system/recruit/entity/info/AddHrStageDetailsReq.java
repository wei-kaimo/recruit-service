package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 02:10
 */
public class AddHrStageDetailsReq {
    private String stageId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String applyForId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    /*@NotBlank(message = ErrorMsg.SOS00000001)
    private String initiatorExamine;*/
    /*@NotBlank(message = ErrorMsg.SOS00000001)
    private String state;*/
    /*@NotBlank(message = ErrorMsg.SOS00000001)
    private String processorId;*/
    /*@NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;*/
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String operationType;


    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getApplyForId() {
        return applyForId;
    }

    public void setApplyForId(String applyForId) {
        this.applyForId = applyForId;
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

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
}
