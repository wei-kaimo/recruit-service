package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 03:21
 */
public class SendReexamineEmailReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String resumeId; //简历ID
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String interviewTime; //面试时间
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String stageName; //本阶段名
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String nextStageName; //下个阶段名

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public String getNextStageName() {
        return nextStageName;
    }

    public void setNextStageName(String nextStageName) {
        this.nextStageName = nextStageName;
    }

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
