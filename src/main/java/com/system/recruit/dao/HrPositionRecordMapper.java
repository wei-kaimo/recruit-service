package com.system.recruit.dao;

import com.system.recruit.entity.HrPositionRecord;

public interface HrPositionRecordMapper {
    int deleteByPrimaryKey(String applyForId);

    int insert(HrPositionRecord record);

    int insertSelective(HrPositionRecord record);

    HrPositionRecord selectByPrimaryKey(String applyForId);

    int updateByPrimaryKeySelective(HrPositionRecord record);

    int updateByPrimaryKey(HrPositionRecord record);
}