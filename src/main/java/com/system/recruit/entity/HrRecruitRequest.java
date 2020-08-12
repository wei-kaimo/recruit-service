package com.system.recruit.entity;

import java.util.Date;

public class HrRecruitRequest {
    private String requestId;

    private String initiatorId;

    private String initiatorExamine;

    private String employerApproverFlowId;

    private String personnelApproverFlowId;

    private String approverState;

    private String processorId;

    private String positionId;

    private String startTime;

    private String planNum;

    private String processState;

    private String backlogNum;

    private String preliminaryScreeningNum;

    private String alreadyInterviewNum;

    private String alreadyOfferNum;

    private String alreadyEntryNum;

    private String weedOutNum;

    private String completePercent;

    private Date createDate;

    private Date updateDate;

    private String sign;

    private boolean first;

    public HrRecruitRequest() {
        this.first = false;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId == null ? null : initiatorId.trim();
    }

    public String getInitiatorExamine() {
        return initiatorExamine;
    }

    public void setInitiatorExamine(String initiatorExamine) {
        this.initiatorExamine = initiatorExamine == null ? null : initiatorExamine.trim();
    }

    public String getEmployerApproverFlowId() {
        return employerApproverFlowId;
    }

    public void setEmployerApproverFlowId(String employerApproverFlowId) {
        this.employerApproverFlowId = employerApproverFlowId == null ? null : employerApproverFlowId.trim();
    }

    public String getPersonnelApproverFlowId() {
        return personnelApproverFlowId;
    }

    public void setPersonnelApproverFlowId(String personnelApproverFlowId) {
        this.personnelApproverFlowId = personnelApproverFlowId == null ? null : personnelApproverFlowId.trim();
    }

    public String getApproverState() {
        return approverState;
    }

    public void setApproverState(String approverState) {
        this.approverState = approverState == null ? null : approverState.trim();
    }

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId == null ? null : processorId.trim();
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId == null ? null : positionId.trim();
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime == null ? null : startTime.trim();
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum == null ? null : planNum.trim();
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState == null ? null : processState.trim();
    }

    public String getBacklogNum() {
        return backlogNum;
    }

    public void setBacklogNum(String backlogNum) {
        this.backlogNum = backlogNum == null ? null : backlogNum.trim();
    }

    public String getPreliminaryScreeningNum() {
        return preliminaryScreeningNum;
    }

    public void setPreliminaryScreeningNum(String preliminaryScreeningNum) {
        this.preliminaryScreeningNum = preliminaryScreeningNum == null ? null : preliminaryScreeningNum.trim();
    }

    public String getAlreadyInterviewNum() {
        return alreadyInterviewNum;
    }

    public void setAlreadyInterviewNum(String alreadyInterviewNum) {
        this.alreadyInterviewNum = alreadyInterviewNum == null ? null : alreadyInterviewNum.trim();
    }

    public String getAlreadyOfferNum() {
        return alreadyOfferNum;
    }

    public void setAlreadyOfferNum(String alreadyOfferNum) {
        this.alreadyOfferNum = alreadyOfferNum == null ? null : alreadyOfferNum.trim();
    }

    public String getAlreadyEntryNum() {
        return alreadyEntryNum;
    }

    public void setAlreadyEntryNum(String alreadyEntryNum) {
        this.alreadyEntryNum = alreadyEntryNum == null ? null : alreadyEntryNum.trim();
    }

    public String getWeedOutNum() {
        return weedOutNum;
    }

    public void setWeedOutNum(String weedOutNum) {
        this.weedOutNum = weedOutNum == null ? null : weedOutNum.trim();
    }

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent == null ? null : completePercent.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}