package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 22:03
 */
public class AddAllRoleReq {
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMin(value = "10")
    private Integer roleId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String role;
    @NotBlank(message = ErrorMsg.SOS00000001)
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
        this.role = role;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getModules() {
        return modules;
    }

    public void setModules(String modules) {
        this.modules = modules;
    }

    public String getDesctibe() {
        return desctibe;
    }

    public void setDesctibe(String desctibe) {
        this.desctibe = desctibe;
    }
}
