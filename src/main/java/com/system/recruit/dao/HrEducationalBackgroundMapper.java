package com.system.recruit.dao;

import com.system.recruit.entity.HrEducationalBackground;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrEducationalBackgroundMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HrEducationalBackground record);

    int insertList(@Param("list") List<HrEducationalBackground> list);

    int insertSelective(HrEducationalBackground record);

    HrEducationalBackground selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HrEducationalBackground record);

    int updateByPrimaryKey(HrEducationalBackground record);
}