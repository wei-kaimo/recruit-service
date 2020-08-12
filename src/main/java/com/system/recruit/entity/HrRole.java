package com.system.recruit.entity;

public class HrRole {
    private Integer roleId;

    private String role;

    private String roleName;

    private String modules;

    private String desctibe;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules == null ? null : modules.trim();
    }

    public String getDesctibe() {
        return desctibe;
    }

    public void setDesctibe(String desctibe) {
        this.desctibe = desctibe == null ? null : desctibe.trim();
    }


}
