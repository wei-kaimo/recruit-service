package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrPosition;

import java.util.List;

public interface HrPositionMapper {
    int deleteByPrimaryKey(String positionId);

    int insert(HrPosition record);

    int insertSelective(HrPosition record);

    HrPosition selectByPrimaryKey(String positionId);

    List<HrPosition> selectAll();

    List<HrPosition> selectByDepartmentId(String departmentId);

    Page<HrPosition> selectAllByPage();

    int updateByPrimaryKeySelective(HrPosition record);

    int updateByPrimaryKey(HrPosition record);
}
