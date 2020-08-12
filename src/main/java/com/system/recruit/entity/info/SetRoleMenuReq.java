package com.system.recruit.entity.info;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 21:19
 */
public class SetRoleMenuReq {
    @DecimalMin(value = "0")
    private Integer roleId;
    @NotEmpty
    private List<Integer> menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
