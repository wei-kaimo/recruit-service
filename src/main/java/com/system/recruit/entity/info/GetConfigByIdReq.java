package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 21:59
 */
public class GetConfigByIdReq {
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMin(value = "1")
    private Integer configId;

    public Integer getConfigId() {
        return configId;
    }

    public void setConfigId(Integer configId) {
        this.configId = configId;
    }
}
