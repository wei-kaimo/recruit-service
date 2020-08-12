package com.system.recruit.entity.info;

import com.system.recruit.entity.HrTicketFlowApproval;

import java.util.Date;
import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 17:00
 */
public class RecruitReqResp {
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


    private List<HrTicketFlowApproval> employerApproverFlow;

    private List<HrTicketFlowApproval> personnelApproverFlow;

    public String getInitiatorExamine() {
        return initiatorExamine;
    }

    public void setInitiatorExamine(String initiatorExamine) {
        this.initiatorExamine = initiatorExamine;
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
        this.requestId = requestId;
    }

    public String getInitiatorId() {
        return initiatorId;
    }

    public void setInitiatorId(String initiatorId) {
        this.initiatorId = initiatorId;
    }

    public String getEmployerApproverFlowId() {
        return employerApproverFlowId;
    }

    public void setEmployerApproverFlowId(String employerApproverFlowId) {
        this.employerApproverFlowId = employerApproverFlowId;
    }

    public List<HrTicketFlowApproval> getEmployerApproverFlow() {
        return employerApproverFlow;
    }

    public void setEmployerApproverFlow(List<HrTicketFlowApproval> employerApproverFlow) {
        this.employerApproverFlow = employerApproverFlow;
    }

    public List<HrTicketFlowApproval> getPersonnelApproverFlow() {
        return personnelApproverFlow;
    }

    public void setPersonnelApproverFlow(List<HrTicketFlowApproval> personnelApproverFlow) {
        this.personnelApproverFlow = personnelApproverFlow;
    }

    public String getPersonnelApproverFlowId() {
        return personnelApproverFlowId;
    }

    public void setPersonnelApproverFlowId(String personnelApproverFlowId) {
        this.personnelApproverFlowId = personnelApproverFlowId;
    }

    public String getApproverState() {
        return approverState;
    }

    public void setApproverState(String approverState) {
        this.approverState = approverState;
    }

    public String getProcessorId() {
        return processorId;
    }

    public void setProcessorId(String processorId) {
        this.processorId = processorId;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPlanNum() {
        return planNum;
    }

    public void setPlanNum(String planNum) {
        this.planNum = planNum;
    }

    public String getProcessState() {
        return processState;
    }

    public void setProcessState(String processState) {
        this.processState = processState;
    }

    public String getBacklogNum() {
        return backlogNum;
    }

    public void setBacklogNum(String backlogNum) {
        this.backlogNum = backlogNum;
    }

    public String getPreliminaryScreeningNum() {
        return preliminaryScreeningNum;
    }

    public void setPreliminaryScreeningNum(String preliminaryScreeningNum) {
        this.preliminaryScreeningNum = preliminaryScreeningNum;
    }

    public String getAlreadyInterviewNum() {
        return alreadyInterviewNum;
    }

    public void setAlreadyInterviewNum(String alreadyInterviewNum) {
        this.alreadyInterviewNum = alreadyInterviewNum;
    }

    public String getAlreadyOfferNum() {
        return alreadyOfferNum;
    }

    public void setAlreadyOfferNum(String alreadyOfferNum) {
        this.alreadyOfferNum = alreadyOfferNum;
    }

    public String getAlreadyEntryNum() {
        return alreadyEntryNum;
    }

    public void setAlreadyEntryNum(String alreadyEntryNum) {
        this.alreadyEntryNum = alreadyEntryNum;
    }

    public String getWeedOutNum() {
        return weedOutNum;
    }

    public void setWeedOutNum(String weedOutNum) {
        this.weedOutNum = weedOutNum;
    }

    public String getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(String completePercent) {
        this.completePercent = completePercent;
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
