package com.system.recruit.dao;

import com.system.recruit.entity.HrDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrDepartmentMapper {
    int deleteByPrimaryKey(Integer departmentId);

    List<HrDepartment> selectByParentId(Integer parentDepartmentId);

    List<String> selectByParentIds(@Param("parentDepartmentIds") List<String> parentDepartmentIds);

    int insert(HrDepartment record);

    int insertSelective(HrDepartment record);

    String selectByName(String departmentName);

    HrDepartment selectByPrimaryKey(Integer departmentId);

    List<HrDepartment> selectAllDepartment();

    List<HrDepartment> selectDepartmentList(Integer userId);

    int updateByPrimaryKeySelective(HrDepartment record);

    int updateByPrimaryKeyWithBLOBs(HrDepartment record);

    int updateByPrimaryKey(HrDepartment record);
}
