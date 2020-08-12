package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrTicketFlowApproval;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrTicketFlowApprovalMapper {
    int deleteByPrimaryKey(String ticketId);

    int insert(HrTicketFlowApproval record);

    int insertSelective(HrTicketFlowApproval record);

    HrTicketFlowApproval selectByPrimaryKey(String ticketId);

    List<HrTicketFlowApproval> selectByRequestIdType(@Param("requestId") String requestId , @Param("ticketType") String ticketType);

    List<HrTicketFlowApproval> selectByRequestId(@Param("requestId") String requestId );

    Page<HrTicketFlowApproval> selectByApproverId(@Param("approverId") String approverId,@Param("approvalStatus") String approvalStatus);

    int updateByPrimaryKeySelective(HrTicketFlowApproval record);

    int updateByPrimaryKey(HrTicketFlowApproval record);

    int updateStateByReqId(HrTicketFlowApproval record);
}
