package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 02:35
 */
public class SortHrStageDetailsReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageDetailsId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String applyForId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageType;
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMax(value = "1")
    private int type;//0提升排序  1下降排序

    public String getStageDetailsId() {
        return stageDetailsId;
    }

    public void setStageDetailsId(String stageDetailsId) {
        this.stageDetailsId = stageDetailsId;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
