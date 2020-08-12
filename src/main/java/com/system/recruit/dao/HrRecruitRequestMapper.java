package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.GetHrRecruitReqCondition;
import com.system.recruit.entity.HrRecruitRequest;
import com.system.recruit.entity.info.GetRecruitReqFlowMsgResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrRecruitRequestMapper {
    int deleteByPrimaryKey(String requestId);

    int insert(HrRecruitRequest record);

    int insertSelective(HrRecruitRequest record);

    HrRecruitRequest selectByPrimaryKey(String requestId);

    HrRecruitRequest getByKeyState(String requestId);

    GetRecruitReqFlowMsgResp selectFlowMsgById(String requestId);

    Page<HrRecruitRequest> selectByInitiatorId(String initiatorId);

    Page<HrRecruitRequest> selectByCondition(GetHrRecruitReqCondition getHrRecruitReqcondition);

    Page<HrRecruitRequest> selectByConditionToHr(@Param("departmentIds") List<String> departmentIds , @Param("state") String state);

    /*Page<HrRecruitRequest> selectByProcessorId(String processorId);*/

    Page<HrRecruitRequest> selectByStaffId(@Param("staffId") String staffId,@Param("state")String state);

    int updateByPrimaryKeySelective(HrRecruitRequest record);

    int updateStateByKey(@Param("processState") String approverState , @Param("requestId") String requestId);

    int updateByPrimaryKey(HrRecruitRequest record);
}
