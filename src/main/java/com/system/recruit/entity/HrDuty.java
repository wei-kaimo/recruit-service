package com.system.recruit.entity;

public class HrDuty {
    private Integer dutyId;

    private String dutyName;

    private Integer departmentId;

    private Integer dutyHierarchy;

    private String dutyDesctibe;

    public Integer getDutyId() {
        return dutyId;
    }

    public void setDutyId(Integer dutyId) {
        this.dutyId = dutyId;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDutyHierarchy() {
        return dutyHierarchy;
    }

    public void setDutyHierarchy(Integer dutyHierarchy) {
        this.dutyHierarchy = dutyHierarchy;
    }

    public String getDutyDesctibe() {
        return dutyDesctibe;
    }

    public void setDutyDesctibe(String dutyDesctibe) {
        this.dutyDesctibe = dutyDesctibe == null ? null : dutyDesctibe.trim();
    }
}