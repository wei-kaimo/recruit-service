package com.system.recruit.dao;

import com.system.recruit.entity.HrDuty;

import java.util.List;

public interface HrDutyMapper {
    int deleteByPrimaryKey(Integer dutyId);

    int insert(HrDuty record);

    int insertSelective(HrDuty record);

    HrDuty selectByPrimaryKey(Integer dutyId);

    List<HrDuty> selectByDepartmentId(Integer departmentId);

    List<HrDuty> selectAllHrDuty();

    int updateByPrimaryKeySelective(HrDuty record);

    int updateByPrimaryKeyWithBLOBs(HrDuty record);

    int updateByPrimaryKey(HrDuty record);
}
