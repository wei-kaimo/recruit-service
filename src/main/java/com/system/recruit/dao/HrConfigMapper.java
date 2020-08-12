package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrConfigMapper {
    int deleteByPrimaryKey(Integer configId);

    int deleteByKey(Integer configId);

    int insert(HrConfig record);

    int insertSelective(HrConfig record);

    int insertHrConfig(HrConfig record);

    HrConfig selectByPrimaryKey(Integer configId);

    List<HrConfig> selectByType(@Param("configType") String configType , @Param("scope") String scope);

    Page<HrConfig> selectAll();

    int updateByPrimaryKeySelective(HrConfig record);

    int updateByPrimaryKey(HrConfig record);
}
