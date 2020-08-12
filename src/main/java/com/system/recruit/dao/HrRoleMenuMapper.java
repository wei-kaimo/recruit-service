package com.system.recruit.dao;

import com.system.recruit.entity.HrRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrRoleMenuMapper {
    int insert(HrRoleMenu record);
    List<Integer> selectByRoleId (Integer roleId);

    int insertList(@Param("recordList") List<HrRoleMenu> recordList);

    int insertSelective(HrRoleMenu record);

    int deleteByRoleId (Integer roleId);


}
