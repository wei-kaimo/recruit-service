package com.system.recruit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Constant;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.dao.*;
import com.system.recruit.entity.GetHrRecruitReqCondition;
import com.system.recruit.entity.HrRecruitRequest;
import com.system.recruit.entity.HrTicketFlowApproval;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.RecruitReqDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/14 0014 15:22
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RecruitReqDataServiceImpl implements RecruitReqDataService {
    @Resource
    private GetUserDateUtil getUserDateUtil;
    @Resource
    private HrRecruitRequestMapper hrRecruitRequestMapper;
    @Resource
    private HrUserDepartmentMapper hrUserDepartmentMapper;
    @Resource
    private HrDepartmentMapper hrDepartmentMapper;
    @Resource
    private HrTicketFlowApprovalMapper hrTicketFlowApprovalMapper;
    @Resource
    private HrApplyForMapper hrApplyForMapper;

    @Override
    public Map<String, Object> initiateRecruitReq(InitiateRecruitReqReq initiateRecruitReqReq, HttpServletRequest request) {
        HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        String requestId = UUID.randomUUID().toString();
        String ticketId = "";
        Integer userId = hrUser.getUserId();
        if ("1".equals(initiateRecruitReqReq.getNeedNotApproval())){

            hrRecruitRequest.setRequestId(requestId);
            hrRecruitRequest.setInitiatorId(userId.toString());
            hrRecruitRequest.setApproverState("7");
            hrRecruitRequest.setInitiatorExamine(initiateRecruitReqReq.getInitiatorExamine());
            hrRecruitRequest.setPositionId(initiateRecruitReqReq.getPositionId());
            hrRecruitRequest.setPlanNum(initiateRecruitReqReq.getPlanNum());
            hrRecruitRequest.setProcessState("10");
            hrRecruitRequest.setBacklogNum(initiateRecruitReqReq.getPlanNum());
            hrRecruitRequest.setPreliminaryScreeningNum("0");
            hrRecruitRequest.setAlreadyInterviewNum("0");
            hrRecruitRequest.setAlreadyOfferNum("0");
            hrRecruitRequest.setAlreadyEntryNum("0");
            hrRecruitRequest.setWeedOutNum("0");
            hrRecruitRequest.setCompletePercent("0");
            hrRecruitRequest.setProcessorId(userId.toString());
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hrRecruitRequest.setStartTime(sdf.format(d));
            hrRecruitRequestMapper.insertSelective(hrRecruitRequest);
            HrRecruitRequest thisHrRecruitRequest = new HrRecruitRequest();
            thisHrRecruitRequest.setRequestId(requestId);
            thisHrRecruitRequest.setProcessState("10");
            thisHrRecruitRequest.setApproverState("7");
            return ResultVO.result(ResultEnum.SUCCESS, thisHrRecruitRequest,true);
        }
        Integer superiorId = hrUserDepartmentMapper.selectSuperiorByUserId(userId);//获取当前用户所属部门老大
        int i = 0;
        if (superiorId != null && !"".equals(superiorId) && superiorId > 2) {//当前用户非admin或大boss
            //循环生成上级部门审批流 直到2级部门结束
            while (superiorId != null && !"".equals(superiorId) && superiorId > 2) {
                i++;
                HrTicketFlowApproval hrTicketFlowApproval = null;
                if (i == 1) {
                    hrTicketFlowApproval = getHrTicketFlowApproval(requestId, userId, superiorId, "2", "1", i);
                    ticketId = hrTicketFlowApproval.getTicketId();
                } else {
                    hrTicketFlowApproval = getHrTicketFlowApproval(requestId, userId, superiorId, "1", "1", i);
                }
                hrTicketFlowApprovalMapper.insertSelective(hrTicketFlowApproval);
                superiorId = hrUserDepartmentMapper.selectDepartmentSuperiorByUserId(superiorId);

            }
        } else if (superiorId != null && !"".equals(superiorId) && superiorId <= 2) {//当前用户为admin或大boss
            i++;
            HrTicketFlowApproval hrTicketFlowApproval = getHrTicketFlowApproval(requestId, userId, superiorId, "2", "1", i);
            hrTicketFlowApprovalMapper.insertSelective(hrTicketFlowApproval);
            ticketId = hrTicketFlowApproval.getTicketId();
        }
        //生成总经办审批流
        Integer managerId = hrUserDepartmentMapper.selectManagerId();
        i++;
        HrTicketFlowApproval hrTicketFlowApproval = getHrTicketFlowApproval(requestId, userId, managerId, "1", "1", i);
        hrTicketFlowApprovalMapper.insertSelective(hrTicketFlowApproval);

        hrRecruitRequest.setRequestId(requestId);
        hrRecruitRequest.setInitiatorId(userId.toString());
        hrRecruitRequest.setEmployerApproverFlowId(ticketId);
        hrRecruitRequest.setApproverState("22");
        hrRecruitRequest.setInitiatorExamine(initiateRecruitReqReq.getInitiatorExamine());
        hrRecruitRequest.setPositionId(initiateRecruitReqReq.getPositionId());
        hrRecruitRequest.setPlanNum(initiateRecruitReqReq.getPlanNum());
        hrRecruitRequest.setProcessState("8");
        hrRecruitRequest.setBacklogNum(initiateRecruitReqReq.getPlanNum());
        hrRecruitRequest.setPreliminaryScreeningNum("0");
        hrRecruitRequest.setAlreadyInterviewNum("0");
        hrRecruitRequest.setAlreadyOfferNum("0");
        hrRecruitRequest.setAlreadyEntryNum("0");
        hrRecruitRequest.setWeedOutNum("0");
        hrRecruitRequest.setCompletePercent("0");

        hrRecruitRequestMapper.insertSelective(hrRecruitRequest);
        return ResultVO.result(ResultEnum.SUCCESS, true);
    }

    @Override
    public GetRecruitReqResp getRecruitReq(GetRecruitReqReq getRecruitReqReq, HttpServletRequest request) {
        String state ="";
        if (getRecruitReqReq.getState() != null && "".equals(getRecruitReqReq.getState())){
            state = getRecruitReqReq.getState();
        }

        GetRecruitReqResp getRecruitReqResp = new GetRecruitReqResp();
        List<RecruitReqResp> recruitReqRespList = new ArrayList<>();
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        Integer userId = hrUser.getUserId();
        List<HrRecruitRequest> hrRecruitRequests = new ArrayList<>();
        Integer superiorId = hrUserDepartmentMapper.selectSuperiorByUserId(userId);
        long totalNum = 0;
        PageHelper.startPage(Integer.parseInt(getRecruitReqReq.getPageNum()), Integer.parseInt(getRecruitReqReq.getPageSize()));
        if ("1".equals(getRecruitReqReq.getType())) {
            GetHrRecruitReqCondition getHrRecruitReqCondition = new GetHrRecruitReqCondition();
            if (!"1".equals(hrUser.getRoleId()) /*&& !"2".equals(hrUser.getRoleId())*/) {
                getHrRecruitReqCondition.setInitiatorId(Integer.toString(userId));
                getHrRecruitReqCondition.setApproverState(state);
            }
            //getHrRecruitReqCondition.setInitiatorId(Integer.toString(userId));
            //getHrRecruitReqCondition.setApproverStatus("2");
            //getHrRecruitReqCondition.setTicketType("1");
            Page<HrRecruitRequest> thisHrRecruitRequests = hrRecruitRequestMapper.selectByCondition(getHrRecruitReqCondition);
            totalNum = thisHrRecruitRequests.getTotal();
            hrRecruitRequests = thisHrRecruitRequests.getResult();
        } else if ("2".equals(getRecruitReqReq.getType())) {
            if (userId.equals(superiorId)) {

                List<String> hrDepartmentIds = new ArrayList<>();
                int i = 0;
                List<String> hrDepartmentIdList = new ArrayList<>();
                if (i == 0) {
                    if (Constant.SYS_CONFIG.get("personnel_manager").equals(String.valueOf(userId))) {
                        hrDepartmentIdList.add("1");
                    } else {
                        hrDepartmentIdList.add(String.valueOf(hrUser.getDepartmentId()));
                    }
                    hrDepartmentIds.add(String.valueOf(hrUser.getDepartmentId()));
                }
                while (hrDepartmentIdList != null && hrDepartmentIdList.size() > 0) {
                    hrDepartmentIdList = hrDepartmentMapper.selectByParentIds(hrDepartmentIdList);
                    boolean flag = hrDepartmentIds.addAll(hrDepartmentIds.size(), hrDepartmentIdList);
                    i++;
                    log.info("添加元素结果：" + flag);
                }
                log.info("共循环" + i + "次！");
                PageHelper.startPage(Integer.parseInt(getRecruitReqReq.getPageNum()), Integer.parseInt(getRecruitReqReq.getPageSize()));
                Page<HrRecruitRequest> thisHrRecruitRequests = hrRecruitRequestMapper.selectByConditionToHr(hrDepartmentIds,state);
                totalNum = thisHrRecruitRequests.getTotal();
                hrRecruitRequests = thisHrRecruitRequests.getResult();
                for (int i1 = 0; i1 < hrRecruitRequests.size(); i1++) {
                    hrRecruitRequests.get(i1).setSign("0");
                    //人事部老大可处理任何请求
                    /*if (Constant.PERSONNEL_MANAGER.equals(userId) ){
                        hrRecruitRequests.get(i1).setSign("1");
                    }*/
                }
            } else {
                PageHelper.startPage(Integer.parseInt(getRecruitReqReq.getPageNum()), Integer.parseInt(getRecruitReqReq.getPageSize()));
                Page<HrRecruitRequest> thisHrRecruitRequests = hrRecruitRequestMapper.selectByStaffId(Integer.toString(userId),state);
                totalNum = thisHrRecruitRequests.getTotal();
                hrRecruitRequests = thisHrRecruitRequests.getResult();
                for (int i = 0; i < hrRecruitRequests.size(); i++) {

                    if (String.valueOf(userId).equals(hrRecruitRequests.get(i).getProcessorId())) {
                        hrRecruitRequests.get(i).setSign("1");
                        log.info("打标记");
                    }
                }
            }
        }
        for (HrRecruitRequest hrRecruitRequest : hrRecruitRequests) {
            List<HrTicketFlowApproval> employerApproverFlow = hrTicketFlowApprovalMapper.selectByRequestIdType(hrRecruitRequest.getRequestId(), "1");
            List<HrTicketFlowApproval> personnelApproverFlow = hrTicketFlowApprovalMapper.selectByRequestIdType(hrRecruitRequest.getRequestId(), "2");
            RecruitReqResp recruitReqResp = new RecruitReqResp();
            recruitReqResp.setRequestId(hrRecruitRequest.getRequestId());
            recruitReqResp.setInitiatorId(hrRecruitRequest.getInitiatorId());
            recruitReqResp.setEmployerApproverFlowId(hrRecruitRequest.getEmployerApproverFlowId());
            recruitReqResp.setEmployerApproverFlow(employerApproverFlow);
            recruitReqResp.setPersonnelApproverFlow(personnelApproverFlow);
            recruitReqResp.setPersonnelApproverFlowId(hrRecruitRequest.getPersonnelApproverFlowId());
            recruitReqResp.setApproverState(hrRecruitRequest.getApproverState());
            recruitReqResp.setProcessorId(hrRecruitRequest.getProcessorId());
            recruitReqResp.setPositionId(hrRecruitRequest.getPositionId());
            recruitReqResp.setStartTime(hrRecruitRequest.getStartTime());
            recruitReqResp.setPlanNum(hrRecruitRequest.getPlanNum());
            recruitReqResp.setProcessState(hrRecruitRequest.getProcessState());
            recruitReqResp.setBacklogNum(hrRecruitRequest.getBacklogNum());
            recruitReqResp.setPreliminaryScreeningNum(hrRecruitRequest.getPreliminaryScreeningNum());
            recruitReqResp.setAlreadyInterviewNum(hrRecruitRequest.getAlreadyInterviewNum());
            recruitReqResp.setAlreadyOfferNum(hrRecruitRequest.getAlreadyOfferNum());
            recruitReqResp.setAlreadyEntryNum(hrRecruitRequest.getAlreadyEntryNum());
            recruitReqResp.setWeedOutNum(hrRecruitRequest.getWeedOutNum());
            recruitReqResp.setCompletePercent(hrRecruitRequest.getCompletePercent());
            recruitReqResp.setSign(hrRecruitRequest.getSign());
            recruitReqResp.setInitiatorExamine(hrRecruitRequest.getInitiatorExamine());
            recruitReqResp.setCreateDate(hrRecruitRequest.getCreateDate());
            recruitReqResp.setUpdateDate(hrRecruitRequest.getUpdateDate());
            recruitReqRespList.add(recruitReqResp);
        }
        for (int i = 0; i < recruitReqRespList.size(); i++) {
            if ("1".equals(recruitReqRespList.get(i).getInitiatorExamine()) && String.valueOf(userId).equals(recruitReqRespList.get(i).getInitiatorId())){
                recruitReqRespList.get(i).setInitiatorExamine("1");
            }else {
                recruitReqRespList.get(i).setInitiatorExamine("0");
            }
        }
        getRecruitReqResp.setRecruitReqRespList(recruitReqRespList);
        getRecruitReqResp.setCount(Long.toString(totalNum));
        return getRecruitReqResp;
    }

    @Override
    public GetRecruitReqFlowResp getRecruitReqFlow(GetRecruitReqFlowReq getRecruitReqFlowReq, HttpServletRequest request) {
        GetRecruitReqFlowResp getRecruitReqFlowResp = new GetRecruitReqFlowResp();
        if ("1".equals(getRecruitReqFlowReq.getState())) {
            getRecruitReqFlowResp.setRecruitReqRespList(new ArrayList<>());
            getRecruitReqFlowResp.setCount("0");
            return getRecruitReqFlowResp;
        }
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        Integer userId = hrUser.getUserId();
        PageHelper.startPage(Integer.parseInt(getRecruitReqFlowReq.getPageNum()), Integer.parseInt(getRecruitReqFlowReq.getPageSize()));
        Page<HrTicketFlowApproval> hrTicketFlowApprovals = hrTicketFlowApprovalMapper.selectByApproverId(String.valueOf(userId), getRecruitReqFlowReq.getState());

        getRecruitReqFlowResp.setRecruitReqRespList(hrTicketFlowApprovals.getResult());
        getRecruitReqFlowResp.setCount(String.valueOf(hrTicketFlowApprovals.getTotal()));
        return getRecruitReqFlowResp;
    }

    @Override
    public Map<String, Object> approvalRecruitReqFlow(ApprovalRecruitReqFlowReq approvalRecruitReqFlowReq, HttpServletRequest request) {
        HrTicketFlowApproval hrTicketFlowApproval = new HrTicketFlowApproval();
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        //流程单状态修改
        hrTicketFlowApproval.setTicketId(approvalRecruitReqFlowReq.getTicketId());
        hrTicketFlowApproval.setApprovalStatus(approvalRecruitReqFlowReq.getApprovalStatus());
        hrTicketFlowApproval.setApprovalOpinion(approvalRecruitReqFlowReq.getApprovalOpinion());
        hrTicketFlowApproval.setApproverId(String.valueOf(hrUser.getUserId()));
        int a = hrTicketFlowApprovalMapper.updateByPrimaryKeySelective(hrTicketFlowApproval);
        //请求拒绝关闭流程
        if ("4".equals(approvalRecruitReqFlowReq.getApprovalStatus())) {
            /*HrTicketFlowApproval thisHrTicketFlowApproval = new HrTicketFlowApproval();
            hrTicketFlowApproval.setRequestId(approvalRecruitReqFlowReq.getRequestId());
            hrTicketFlowApproval.setApprovalStatus("19");
            hrTicketFlowApprovalMapper.updateStateByReqId(thisHrTicketFlowApproval);*/
            HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
            hrRecruitRequest.setApproverState("23");
            hrRecruitRequest.setProcessState("20");
            hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
            Object msg = "请求拒绝";
            return ResultVO.result(ResultEnum.SUCCESS, msg, true);
        }
        //审批通过且为hr审批部分
        if (a > 0 && "2".equals(approvalRecruitReqFlowReq.getTicketType())) {
            HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
            hrRecruitRequest.setRequestId(approvalRecruitReqFlowReq.getRequestId());
            hrRecruitRequest.setProcessorId(approvalRecruitReqFlowReq.getProcessorId());
            hrRecruitRequest.setProcessState("7");
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hrRecruitRequest.setStartTime(sdf.format(d));
            hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
            //TODO 调用面试流程方法 如果成功执行状态修改方法 若失败抛出数据库异常回滚提交  移交AOP处理，事务问题
            HrRecruitRequest thisHrRecruitRequest = new HrRecruitRequest();
            thisHrRecruitRequest.setRequestId(approvalRecruitReqFlowReq.getRequestId());
            thisHrRecruitRequest.setProcessState("10");
            thisHrRecruitRequest.setApproverState("7");

            return ResultVO.result(ResultEnum.SUCCESS, thisHrRecruitRequest, true);
        }
        //非HR审批且已通过
        List<HrTicketFlowApproval> hrTicketFlowApprovals = hrTicketFlowApprovalMapper.selectByRequestId(approvalRecruitReqFlowReq.getRequestId());
        if ("3".equals(approvalRecruitReqFlowReq.getApprovalStatus())) {
            for (int i = 0; i < hrTicketFlowApprovals.size(); i++) {
                //若为最后一个流程生成hr审批流
                if (i == hrTicketFlowApprovals.size() - 1 && "1".equals(hrTicketFlowApprovals.get(i).getTicketType())) {
                    HrTicketFlowApproval lastHrTicketFlowApproval = hrTicketFlowApprovalMapper.selectByPrimaryKey(approvalRecruitReqFlowReq.getTicketId());
                    HrTicketFlowApproval thisHrTicketFlowApproval = getHrTicketFlowApproval(approvalRecruitReqFlowReq.getRequestId()
                            , Integer.valueOf(lastHrTicketFlowApproval.getInitiatorId()), Integer.valueOf(Constant.SYS_CONFIG.get("personnel_manager")),//此处HR的userId使用硬编码；
                            "2", "2", lastHrTicketFlowApproval.getSort() + 1);
                    hrTicketFlowApprovalMapper.insertSelective(thisHrTicketFlowApproval);
                    HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
                    hrRecruitRequest.setApproverState("6");
                    hrRecruitRequest.setRequestId(approvalRecruitReqFlowReq.getRequestId());
                    hrRecruitRequest.setPersonnelApproverFlowId(thisHrTicketFlowApproval.getTicketId());
                    hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
                    break;
                }
                //下个流程掷为开始 且当前流程Id赋值
                if (approvalRecruitReqFlowReq.getTicketId().equals(hrTicketFlowApprovals.get(i).getTicketId())) {
                    HrTicketFlowApproval thisHrTicketFlowApproval = new HrTicketFlowApproval();
                    thisHrTicketFlowApproval.setTicketId(hrTicketFlowApprovals.get(i + 1).getTicketId());
                    thisHrTicketFlowApproval.setApprovalStatus("2");
                    hrTicketFlowApprovalMapper.updateByPrimaryKeySelective(thisHrTicketFlowApproval);
                    HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
                    hrRecruitRequest.setRequestId(approvalRecruitReqFlowReq.getRequestId());
                    hrRecruitRequest.setEmployerApproverFlowId(thisHrTicketFlowApproval.getTicketId());
                    hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
                    break;
                }
            }
        }
        if (a <= 0) {
            return ResultVO.failure(ResultEnum.FAILURE, a, false);
        }
        return ResultVO.result(ResultEnum.SUCCESS, a, true);
    }

    @Override
    public GetRecruitReqFlowMsgResp getRecruitReqFlowMsg(GetRecruitReqFlowMsgReq getRecruitReqFlowMsgReq) {
        return hrRecruitRequestMapper.selectFlowMsgById(getRecruitReqFlowMsgReq.getRequestId());
    }

    @Override
    public Map<String, Object> closeRecruitReq(CloseRecruitReqReq closeRecruitReqReq, HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        HrRecruitRequest hrRecruitRequest = hrRecruitRequestMapper.selectByPrimaryKey(closeRecruitReqReq.getRequestId());
        if (!String.valueOf(hrUser.getUserId()).equals(hrRecruitRequest.getProcessorId())){
            return ResultVO.result(ResultEnum.USER_NO_ACCESS,false);
        }
        hrApplyForMapper.deleteByState(closeRecruitReqReq.getRequestId());
        int a = hrRecruitRequestMapper.updateStateByKey(closeRecruitReqReq.getState(),closeRecruitReqReq.getRequestId());
        return ResultVO.result(ResultEnum.SUCCESS, a, true);
    }

    private HrTicketFlowApproval getHrTicketFlowApproval(String requestId, Integer initiatorId, Integer approverId, String approvalStatus, String type, Integer sort) {
        HrTicketFlowApproval hrTicketFlowApproval = new HrTicketFlowApproval();
        hrTicketFlowApproval.setTicketId(UUID.randomUUID().toString());
        hrTicketFlowApproval.setRequestId(requestId);
        hrTicketFlowApproval.setInitiatorId(initiatorId.toString());
        hrTicketFlowApproval.setApproverId(approverId.toString());
        hrTicketFlowApproval.setApprovalStatus(approvalStatus);
        hrTicketFlowApproval.setTicketType(type);
        hrTicketFlowApproval.setSort(sort);
        return hrTicketFlowApproval;
    }


}
