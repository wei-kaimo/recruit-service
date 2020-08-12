package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 21:14
 */
public class GetFlowConfigReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String type; // 所属阶段

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
