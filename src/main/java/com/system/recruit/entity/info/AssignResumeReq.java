package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 03:35
 */
public class AssignResumeReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String applyForId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String resumeId;

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

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
}
