package com.system.recruit.entity.info;

import com.system.recruit.entity.HrFlowConfig;

import java.util.Date;
import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:54
 */
public class HrPositionResp {
    private String positionId;

    private String positionName;

    private String departmentId;

    private String cityName;

    private String jobNature;

    private String positionRequirements;

    private String positionResponsibilities;

    private Date createDate;

    private Date updateDate;

    public String getPositionRequirements() {
        return positionRequirements;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements;
    }

    public String getPositionResponsibilities() {
        return positionResponsibilities;
    }

    public void setPositionResponsibilities(String positionResponsibilities) {
        this.positionResponsibilities = positionResponsibilities;
    }

    private List<HrFlowConfig> flowConfigList;

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
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

    public List<HrFlowConfig> getFlowConfigList() {
        return flowConfigList;
    }

    public void setFlowConfigList(List<HrFlowConfig> flowConfigList) {
        this.flowConfigList = flowConfigList;
    }
}
