package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrApplyFor;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrApplyForMapper {
    int deleteByPrimaryKey(String applyForId);

    int deleteByState(String requestId);

    int insert(HrApplyFor record);

    int insertList(@Param("hrApplyForList")List<HrApplyFor> hrApplyForList);

    int insertSelective(HrApplyFor record);

    HrApplyFor selectByPrimaryKey(String applyForId);

    Page<HrApplyFor> selectByRequestIdState(@Param("requestId") String requestId , @Param("stateId") String stateId);

    List<HrApplyFor> getCloseByRequestId(@Param("requestId") String requestId);

    List<HrApplyFor> selectAllByRequestId(@Param("requestId") String requestId);

    int updateByPrimaryKeySelective(HrApplyFor record);

    int updateByPrimaryKey(HrApplyFor record);

    int getBadNumByReqId(String requestId);

    int getAllNumByReqId(String requestId);
}