package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/7 17:53
 */
public class DeleteDutyReq {
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMin(value = "0")
    private Integer dutyId;

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }
}
