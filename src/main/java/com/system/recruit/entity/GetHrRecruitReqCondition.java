package com.system.recruit.entity;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 18:22
 */
public class GetHrRecruitReqCondition {
    private String approverId;
    private String initiatorId;
    private String approverState;
    private String processorId;
    private String ticketType;

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public String getApproverState() {
        return approverState;
    }

    public void setApproverState(String approverState) {
        this.approverState = approverState;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }
}
