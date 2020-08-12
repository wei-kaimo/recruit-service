package com.system.recruit.dao;

import com.system.recruit.entity.HrFlowConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrFlowConfigMapper {
    int deleteByPrimaryKey(String stageId);

    int deleteByPosition(String positionId);

    int insert(HrFlowConfig record);

    int insertSelective(HrFlowConfig record);

    HrFlowConfig selectByPrimaryKey(String stageId);

    List<HrFlowConfig> selectByPositionId(String positionId);

    List<HrFlowConfig> selectByPositionIdStageType(@Param("positionId") String positionId , @Param("stageType") String stageType);

    int updateByPrimaryKeySelective(HrFlowConfig record);

    int updateByPrimaryKey(HrFlowConfig record);
}
