package com.system.recruit.entity.info;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/27 16:34
 */
public class GetUserByRoleIdReq {
    private List<String> roleIds;

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }
}
