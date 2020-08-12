package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 22:13
 */
public class SortFlowConfigReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMax(value = "1")
    private int type;//0提升排序  1下降排序

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
