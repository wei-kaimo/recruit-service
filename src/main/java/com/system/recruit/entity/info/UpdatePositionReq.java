package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:04
 */
public class UpdatePositionReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String departmentId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String cityName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String jobNature;
    private String positionRequirements;

    private String positionResponsibilities;

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
}
