package com.system.recruit.entity;

import java.util.List;

public class HrDepartment {
    private Integer departmentId;

    private String departmentName;

    private Integer parentDepartmentId;

    private String departmentDesctibe;

    private List<HrDepartment> children;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Integer parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getDepartmentDesctibe() {
        return departmentDesctibe;
    }

    public void setDepartmentDesctibe(String departmentDesctibe) {
        this.departmentDesctibe = departmentDesctibe == null ? null : departmentDesctibe.trim();
    }

    public List<HrDepartment> getChildren() {
        return children;
    }

    public void setChildren(List<HrDepartment> children) {
        this.children = children;
    }
}
