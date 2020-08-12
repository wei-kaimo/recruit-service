package com.system.recruit.dao;

import com.system.recruit.entity.HrFlowConfig;
import com.system.recruit.entity.HrFlowConfigRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrFlowConfigRecordMapper {
    int deleteByPrimaryKey(String applyForId);

    int insert(HrFlowConfigRecord record);

    int insertList(@Param("hrFlowConfigRecords") List<HrFlowConfigRecord> hrFlowConfigRecords);

    int insertSelective(HrFlowConfigRecord record);

    HrFlowConfigRecord selectByPrimaryKey(String applyForId);

    int updateByPrimaryKeySelective(HrFlowConfigRecord record);

    int updateByPrimaryKey(HrFlowConfigRecord record);

    List<HrFlowConfig> selectByPositionId(@Param("requestId") String requestId , @Param("positionId")String positionId);
}