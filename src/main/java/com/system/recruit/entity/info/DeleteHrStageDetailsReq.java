package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 02:00
 */
public class DeleteHrStageDetailsReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageDetailsId;

    public String getStageDetailsId() {
        return stageDetailsId;
    }

    public void setStageDetailsId(String stageDetailsId) {
        this.stageDetailsId = stageDetailsId;
    }
}
