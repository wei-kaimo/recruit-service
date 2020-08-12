package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/13 0013 17:57
 */
public class GetRoleMenuReq {
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
