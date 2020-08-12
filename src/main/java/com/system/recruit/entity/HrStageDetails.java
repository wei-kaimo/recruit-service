package com.system.recruit.entity;

import java.util.Date;

public class HrStageDetails {
    private String stageDetailsId;

    private String stageId;

    private String applyForId;

    private String stageName;

    private String stageType;

    private String state;

    private String processorId;

    private String requestId;

    private String remark;

    private int sort;

    private String operationType;

    private String initiatorExamine;

    private Date createDate;

    private Date updateDate;

    public String getInitiatorExamine() {
        return initiatorExamine;
    }

    public void setInitiatorExamine(String initiatorExamine) {
        this.initiatorExamine = initiatorExamine;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getStageDetailsId() {
        return stageDetailsId;
    }

    public void setStageDetailsId(String stageDetailsId) {
        this.stageDetailsId = stageDetailsId == null ? null : stageDetailsId.trim();
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId == null ? null : stageId.trim();
    }

    public String getApplyForId() {
        return applyForId;
    }

    public void setApplyForId(String applyForId) {
        this.applyForId = applyForId == null ? null : applyForId.trim();
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName == null ? null : stageName.trim();
    }

    public String getStageType() {
        return stageType;
    }

    public void setStageType(String stageType) {
        this.stageType = stageType == null ? null : stageType.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId == null ? null : processorId.trim();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId == null ? null : requestId.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
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