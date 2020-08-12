package com.system.recruit.dao;

import com.system.recruit.entity.HrWorkExperience;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrWorkExperienceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrWorkExperience record);

    int insertList(@Param("list") List<HrWorkExperience> record);

    int insertSelective(HrWorkExperience record);

    HrWorkExperience selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrWorkExperience record);

    int updateByPrimaryKey(HrWorkExperience record);
}