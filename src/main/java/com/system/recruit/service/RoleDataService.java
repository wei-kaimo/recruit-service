package com.system.recruit.service;

import com.system.recruit.entity.HrRole;
import com.system.recruit.entity.info.*;

import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 21:13
 */
public interface RoleDataService {
    GetAllRoleResp getAllRole(GetAllRoleReq getAllRoleReq);
    Map<String,Object> setRoleMenu(SetRoleMenuReq setRoleMenuReq);
    Map<String,Object> addAllRole(AddAllRoleReq addAllRoleReq);
    List<HrRole> getRole();

    List<GetRoleMenuResp> getRoleMenu(GetRoleMenuReq getRoleMenuReq);

    Map<String, Object> deleteRole(DeleteRole deleteRole);
}
