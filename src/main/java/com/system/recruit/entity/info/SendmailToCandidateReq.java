package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 02:50
 */
public class SendmailToCandidateReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String type;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String applyForId; //阶段性详情Id
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String interviewTime; //面试时间
    private String stageName; //本阶段名
    private String nextStageName; //下个阶段名

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getApplyForId() {
        return applyForId;
    }

    public void setApplyForId(String applyForId) {
        this.applyForId = applyForId;
    }

    public String getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        this.interviewTime = interviewTime;
    }

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
}
