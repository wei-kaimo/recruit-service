package com.system.recruit.dao;

import com.system.recruit.entity.HrUserRole;

public interface HrUserRoleMapper {
    int insert(HrUserRole record);

    int deleteUserRole(Integer userId);

    int insertSelective(HrUserRole record);

    int updateUserRole(HrUserRole record);
}
