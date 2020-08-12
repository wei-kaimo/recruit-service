package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/6 00:57
 */
public class CloseRecruitReqReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
