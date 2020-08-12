package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrRole;

import java.util.List;

public interface HrRoleMapper {
    int deleteByPrimaryKey(Integer roleId);

    int insert(HrRole record);

    int insertSelective(HrRole record);

    HrRole selectByPrimaryKey(Integer roleId);

    Page<HrRole> selectAllRole();

    List<HrRole> selectRole();

    int updateByPrimaryKeySelective(HrRole record);

    int updateByPrimaryKeyWithBLOBs(HrRole record);

    int updateByPrimaryKey(HrRole record);
}
