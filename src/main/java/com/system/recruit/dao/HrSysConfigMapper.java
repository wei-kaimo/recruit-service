package com.system.recruit.dao;

import com.system.recruit.entity.HrSysConfig;

import java.util.List;

public interface HrSysConfigMapper {
    int deleteByPrimaryKey(String sysConfigName);

    int insert(HrSysConfig record);

    int insertSelective(HrSysConfig record);

    String selectByPrimaryKey(String sysConfigName);

    List<HrSysConfig> getAllByPrimaryKey();

    int updateByPrimaryKeySelective(HrSysConfig record);

    int updateByPrimaryKey(HrSysConfig record);
}