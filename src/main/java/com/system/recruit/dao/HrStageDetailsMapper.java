package com.system.recruit.dao;

import com.system.recruit.entity.HrStageDetails;
import com.system.recruit.entity.info.GetHrStageDetailsReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrStageDetailsMapper {
    int deleteByPrimaryKey(String stageDetailsId);

    int insert(HrStageDetails record);

    int insertList(@Param("hrStageDetails") List<HrStageDetails> hrStageDetails);

    int insertSelective(HrStageDetails record);

    HrStageDetails selectByPrimaryKey(String stageDetailsId);

    int updateByPrimaryKeySelective(HrStageDetails record);

    int updateByPrimaryKey(HrStageDetails record);

    List<HrStageDetails> selectByType(GetHrStageDetailsReq getHrStageDetailsReq);

    List<HrStageDetails> selectByApplyForId(String applyForId);

}