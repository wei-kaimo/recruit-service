package com.system.recruit.dao;

import com.system.recruit.entity.HrUserDepartment;

public interface HrUserDepartmentMapper {
    int insert(HrUserDepartment record);

    Integer selectManagerId();

    HrUserDepartment selectById(Integer userId);

    int deleteByUserId(Integer userId);

    int insertSelective(HrUserDepartment record);

    int updateSelective(HrUserDepartment record);

    Integer selectSuperiorByUserId(Integer userId);
    Integer selectDepartmentSuperiorByUserId(Integer userId);
}
