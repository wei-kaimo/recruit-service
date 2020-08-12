package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/26 16:32
 */
public class SendInterviewEmailReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String resumeId; //简历ID
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String interviewTime; //面试时间

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }
}
