package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 17:55
 */
public class CorrectResumeReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String resumeId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String sort;
    //private String taster;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String state;
    private String remark;

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
