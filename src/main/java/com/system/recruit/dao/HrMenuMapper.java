package com.system.recruit.dao;

import com.system.recruit.entity.HrMenu;
import com.system.recruit.entity.info.GetRoleMenuResp;

import java.util.List;

public interface HrMenuMapper {
    int deleteByPrimaryKey(Integer menuId);

    int deleteByKeyList(List<Integer> menuIds);


    int insert(HrMenu record);

    int insertSelective(HrMenu record);

    HrMenu selectByPrimaryKey(Integer menuId);

    GetRoleMenuResp selectRoleMenuByKey(Integer menuId);

    List<HrMenu> selectByParentId(Integer parentId);

    List<HrMenu> getMenuListByRoleId(Integer roleId);

    List<HrMenu> selectAllMenu();

    List<GetRoleMenuResp> selectAllRoleMenu();

    int updateByPrimaryKeySelective(HrMenu record);

    int updateByPrimaryKeyWithBLOBs(HrMenu record);

    int updateByPrimaryKey(HrMenu record);
}
