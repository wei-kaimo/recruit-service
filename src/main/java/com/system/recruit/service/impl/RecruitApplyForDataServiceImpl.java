package com.system.recruit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.dao.*;
import com.system.recruit.entity.*;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.MailService;
import com.system.recruit.service.RecruitApplyForDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/28 12:35
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class RecruitApplyForDataServiceImpl implements RecruitApplyForDataService {
    @Resource
    private HrApplyForMapper hrApplyForMapper;

    @Resource
    private HrRecruitRequestMapper hrRecruitRequestMapper;

    @Resource
    private HrFlowConfigMapper hrFlowConfigMapper;

    @Resource
    private HrFlowConfigRecordMapper hrFlowConfigRecordMapper;

    @Resource
    private HrPositionMapper hrPositionMapper;

    @Resource
    private HrPositionRecordMapper hrPositionRecordMapper;

    @Resource
    private HrStageDetailsMapper hrStageDetailsMapper;

    @Resource
    private GetUserDateUtil getUserDateUtil;

    @Resource
    private MailService mailService;
    @Resource
    private HrRequestResumeMapper hrRequestResumeMapper;

    @Override
    public GetRecruitApplyForResp getRecruitApplyFor(GetRecruitApplyForReq getRecruitApplyForReq, HttpServletRequest request) {
        PageHelper.startPage(Integer.parseInt(getRecruitApplyForReq.getPageNum()), Integer.parseInt(getRecruitApplyForReq.getPageSize()));
        String stateId = "";
        if (getRecruitApplyForReq.getStateId() != null && "".equals(getRecruitApplyForReq.getStateId())){
            stateId = getRecruitApplyForReq.getStateId();
        }
        Page<HrApplyFor> hrApplyForList = hrApplyForMapper.selectByRequestIdState(getRecruitApplyForReq.getRequestId(), stateId);
        GetRecruitApplyForResp getRecruitApplyForResp =new GetRecruitApplyForResp();
        getRecruitApplyForResp.setCount(String.valueOf(hrApplyForList.getTotal()));
        getRecruitApplyForResp.setHrApplyForList(hrApplyForList.getResult());

        return getRecruitApplyForResp;
    }

    @Override
    public void generateRecruitApplyFor(HrRecruitRequest hrRecruitRequest) {
        List<HrApplyFor> hrApplyForList = new ArrayList<>();

        HrRecruitRequest thisHrRecruitRequest = hrRecruitRequestMapper.getByKeyState(hrRecruitRequest.getRequestId());
        int n = 0;
        int planNum = Integer.valueOf(thisHrRecruitRequest.getPlanNum());
        /*int allNum = hrApplyForMapper.getAllNumByReqId(thisHrRecruitRequest.getRequestId());
        int badNum = hrApplyForMapper.getBadNumByReqId(thisHrRecruitRequest.getRequestId());
        if (allNum > 0 && (allNum - badNum) > 0) {
            n = badNum;
        } else if (allNum == 0 || planNum == badNum) {
            n = planNum;
        }*/
        if (hrRecruitRequest.isFirst()){
            n = planNum;
        }else {
            n = 1;
        }
        for (int i = 0; i < n; i++) {
            HrApplyFor hrApplyFor = new HrApplyFor();
            hrApplyFor.setRequestId(thisHrRecruitRequest.getRequestId());
            hrApplyFor.setInitiatorId(thisHrRecruitRequest.getInitiatorId());
            hrApplyFor.setPositionId(thisHrRecruitRequest.getPositionId());
            hrApplyFor.setProcessorId(thisHrRecruitRequest.getProcessorId());
            hrApplyFor.setStateId("16");
            String applyForId = UUID.randomUUID().toString();
            log.info(applyForId);
            hrApplyFor.setApplyForId(applyForId);
            hrApplyForList.add(hrApplyFor);
        }
        hrApplyForMapper.insertList(hrApplyForList);

        List<HrFlowConfig> hrFlowConfigs = new ArrayList<>();
        if (hrRecruitRequest.isFirst()){
            hrFlowConfigs = hrFlowConfigMapper.selectByPositionId(thisHrRecruitRequest.getPositionId());
        }else {
            hrFlowConfigs = hrFlowConfigRecordMapper.selectByPositionId(thisHrRecruitRequest.getRequestId(),thisHrRecruitRequest.getPositionId());
        }
         //hrFlowConfigMapper.selectByPositionId(thisHrRecruitRequest.getPositionId());

        List<HrStageDetails> hrStageDetailsList = new ArrayList<>();
        for (HrApplyFor applyFor : hrApplyForList) {
            String applyForId = applyFor.getApplyForId();
            int i = 0;
            for (HrFlowConfig hrFlowConfig : hrFlowConfigs) {
                HrStageDetails hrStageDetails = new HrStageDetails();
                hrStageDetails.setApplyForId(applyForId);
                hrStageDetails.setState("24");
                hrStageDetails.setProcessorId(thisHrRecruitRequest.getProcessorId());
                hrStageDetails.setRequestId(thisHrRecruitRequest.getRequestId());
                i++;
                hrStageDetails.setStageDetailsId(UUID.randomUUID().toString());
                hrStageDetails.setStageId(hrFlowConfig.getStageId());
                hrStageDetails.setStageName(hrFlowConfig.getStageName());
                hrStageDetails.setStageType(hrFlowConfig.getStageType());
                hrStageDetails.setOperationType(hrFlowConfig.getOperationType());
                hrStageDetails.setInitiatorExamine(thisHrRecruitRequest.getInitiatorExamine());
                hrStageDetails.setSort(hrFlowConfig.getSort());
                if (i == 1) {
                    hrStageDetails.setState("25");
                }
                hrStageDetailsList.add(hrStageDetails);
            }
        }
        hrStageDetailsMapper.insertList(hrStageDetailsList);
        if (hrRecruitRequest.isFirst()) {
            List<HrFlowConfigRecord> hrFlowConfigRecords = new ArrayList<>();
            for (HrFlowConfig hrFlowConfig : hrFlowConfigs) {
                HrFlowConfigRecord hrFlowConfigRecord = new HrFlowConfigRecord();
                hrFlowConfigRecord.setRequestId(thisHrRecruitRequest.getRequestId());
                hrFlowConfigRecord.setStageId(hrFlowConfig.getStageId());
                hrFlowConfigRecord.setPositionId(hrFlowConfig.getPositionId());
                hrFlowConfigRecord.setStageName(hrFlowConfig.getStageName());
                hrFlowConfigRecord.setStageType(hrFlowConfig.getStageType());
                hrFlowConfigRecord.setOperationType(hrFlowConfig.getOperationType());
                hrFlowConfigRecord.setSort(hrFlowConfig.getSort());
                hrFlowConfigRecord.setCreateDate(hrFlowConfig.getCreateDate());
                hrFlowConfigRecord.setUpdateDate(hrFlowConfig.getUpdateDate());
                hrFlowConfigRecords.add(hrFlowConfigRecord);
            }
            hrFlowConfigRecordMapper.insertList(hrFlowConfigRecords);

            HrPosition hrPosition = hrPositionMapper.selectByPrimaryKey(thisHrRecruitRequest.getPositionId());
            HrPositionRecord hrPositionRecord = new HrPositionRecord();
            hrPositionRecord.setRequestId(hrRecruitRequest.getRequestId());
            hrPositionRecord.setPositionId(hrPosition.getPositionId());
            hrPositionRecord.setPositionName(hrPosition.getPositionName());
            hrPositionRecord.setCityName(hrPosition.getCityName());
            hrPositionRecord.setJobNature(hrPosition.getJobNature());
            hrPositionRecord.setDepartmentId(hrPosition.getDepartmentId());
            hrPositionRecord.setPositionRequirements(hrPosition.getPositionRequirements());
            hrPositionRecord.setPositionResponsibilities(hrPosition.getPositionResponsibilities());
            hrPositionRecord.setCreateDate(hrPosition.getCreateDate());
            hrPositionRecord.setUpdateDate(hrPosition.getUpdateDate());
            hrPositionRecordMapper.insertSelective(hrPositionRecord);
        }
    }

    @Override
    public Map<String, Object> deleteHrStageDetails(DeleteHrStageDetailsReq deleteHrStageDetailsReq) {
        int a = hrStageDetailsMapper.deleteByPrimaryKey(deleteHrStageDetailsReq.getStageDetailsId());
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> addHrStageDetails(AddHrStageDetailsReq addHrStageDetailsReq, HttpServletRequest request) {
        GetHrStageDetailsReq getHrStageDetailsReq = new GetHrStageDetailsReq();
        getHrStageDetailsReq.setApplyForId(addHrStageDetailsReq.getApplyForId());
        getHrStageDetailsReq.setStageType(addHrStageDetailsReq.getStageType());
        List<HrStageDetails> hrStageDetailsList = hrStageDetailsMapper.selectByType(getHrStageDetailsReq);
        Integer sort = (hrStageDetailsList.get(hrStageDetailsList.size()-1).getSort())+1;
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        HrStageDetails hrStageDetails = new HrStageDetails();
        hrStageDetails.setStageDetailsId(UUID.randomUUID().toString());
        hrStageDetails.setStageId(UUID.randomUUID().toString()+"userId"+hrUser.getUserId());
        hrStageDetails.setApplyForId(addHrStageDetailsReq.getApplyForId());
        hrStageDetails.setStageName(addHrStageDetailsReq.getStageName());
        hrStageDetails.setStageType(addHrStageDetailsReq.getStageType());
        hrStageDetails.setState("24");
        hrStageDetails.setProcessorId(String.valueOf(hrUser.getUserId()));
        hrStageDetails.setRequestId(hrStageDetailsList.get(hrStageDetailsList.size()-1).getRequestId());
        hrStageDetails.setOperationType(addHrStageDetailsReq.getOperationType());
        hrStageDetails.setInitiatorExamine(hrStageDetailsList.get(hrStageDetailsList.size()-1).getInitiatorExamine());
        hrStageDetails.setSort(sort);

        int a = hrStageDetailsMapper.insertSelective(hrStageDetails);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> sortHrStageDetails(SortHrStageDetailsReq sortHrStageDetailsReq) {
        GetHrStageDetailsReq getHrStageDetailsReq = new GetHrStageDetailsReq();
        getHrStageDetailsReq.setApplyForId(sortHrStageDetailsReq.getApplyForId());
        getHrStageDetailsReq.setStageType(sortHrStageDetailsReq.getStageType());
        List<HrStageDetails> hrStageDetailsList = hrStageDetailsMapper.selectByType(getHrStageDetailsReq);
        HrStageDetails affectHrStageDetails = null;
        HrStageDetails hrStageDetails = null;
        for (int i = 0; i < hrStageDetailsList.size(); i++) {
            if (sortHrStageDetailsReq.getType() == 0 && i==0){
                continue;
            }
            if (sortHrStageDetailsReq.getType() == 1 && i==hrStageDetailsList.size()-1){
                continue;
            }
            hrStageDetails = hrStageDetailsList.get(i);
            Integer medianSort = null;
            if (sortHrStageDetailsReq.getStageDetailsId().equals(hrStageDetails.getStageDetailsId())){
                if (sortHrStageDetailsReq.getType() == 0){
                    affectHrStageDetails = hrStageDetailsList.get(i-1);
                }
                if (sortHrStageDetailsReq.getType() == 1){
                    affectHrStageDetails = hrStageDetailsList.get(i+1);
                }
                medianSort =affectHrStageDetails.getSort();
                affectHrStageDetails.setSort(hrStageDetails.getSort());
                hrStageDetails.setSort(medianSort);
                break;
            }
        }
        if (affectHrStageDetails==null){
            return ResultVO.failure(ResultEnum.FAILURE,"超出范围",false);
        }else {
            int a = hrStageDetailsMapper.updateByPrimaryKeySelective(affectHrStageDetails);
            int b = hrStageDetailsMapper.updateByPrimaryKeySelective(hrStageDetails);
        }
        return ResultVO.result(ResultEnum.SUCCESS,true);
    }

    @Override
    public Map<String, Object> approveHrStageDetails(ApproveHrStageDetailsReq approveHrStageDetailsReq, HttpServletRequest request) {
        HrRecruitRequest hrRecruitRequest = new HrRecruitRequest();
        hrRecruitRequest.setFirst(false);
        HrStageDetails firstHrStageDetails = hrStageDetailsMapper.selectByPrimaryKey(approveHrStageDetailsReq.getStageDetailsId());
        String state = approveHrStageDetailsReq.getState();
        HrStageDetails hrStageDetails = new HrStageDetails();
        hrStageDetails.setStageDetailsId(approveHrStageDetailsReq.getStageDetailsId());
        hrStageDetails.setState(state);
        if ("26".equals(state)){
            List<HrStageDetails> hrStageDetailsList = hrStageDetailsMapper.selectByApplyForId(firstHrStageDetails.getApplyForId());
            for (int i = 0; i < hrStageDetailsList.size(); i++) {
                if (hrStageDetailsList.get(i).getStageDetailsId().equals(approveHrStageDetailsReq.getStageDetailsId())){
                    if (i == hrStageDetailsList.size()-1){
                        HrApplyFor hrApplyFor = new HrApplyFor();
                        hrApplyFor.setApplyForId(firstHrStageDetails.getApplyForId());
                        hrApplyFor.setStateId("18");
                        hrApplyForMapper.updateByPrimaryKeySelective(hrApplyFor);
                    }else {
                        HrStageDetails thisHrStageDetails = new HrStageDetails();
                        thisHrStageDetails.setStageDetailsId(hrStageDetailsList.get(i+1).getStageDetailsId());
                        thisHrStageDetails.setState("25");
                        hrStageDetailsMapper.updateByPrimaryKeySelective(thisHrStageDetails);
                    }
                }
            }
        }
        if ("27".equals(state)){
                        HrApplyFor hrApplyFor = new HrApplyFor();
                        hrApplyFor.setApplyForId(firstHrStageDetails.getApplyForId());
                        hrApplyFor.setStateId("17");
                        hrApplyForMapper.updateByPrimaryKeySelective(hrApplyFor);
            hrRecruitRequest.setRequestId(firstHrStageDetails.getRequestId());
            //generateRecruitApplyFor(hrRecruitRequest);
        }
        int a = hrStageDetailsMapper.updateByPrimaryKeySelective(hrStageDetails);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        if (hrRecruitRequest.getRequestId()==null || "".equals(hrRecruitRequest.getRequestId())){
            return ResultVO.result(ResultEnum.SUCCESS,a,true);
        }
        return ResultVO.result(ResultEnum.SUCCESS,hrRecruitRequest,true);
    }

    @Override
    public Map<String, Object> SendmailToCandidateReq(SendmailToCandidateReq sendmailToCandidateReq, HttpServletRequest request) {
        String msg = "没有匹配模板，发送失败";
        HrApplyFor hrApplyFor = hrApplyForMapper.selectByPrimaryKey(sendmailToCandidateReq.getApplyForId());
        if ("39".equals(sendmailToCandidateReq.getType())){
            SendInterviewEmailReq sendInterviewEmailReq = new SendInterviewEmailReq();
            sendInterviewEmailReq.setResumeId(hrApplyFor.getResumeId());
            sendInterviewEmailReq.setInterviewTime(sendmailToCandidateReq.getInterviewTime());
            return mailService.sendInterviewEmail(sendInterviewEmailReq,request);
        }else if ("40".equals(sendmailToCandidateReq.getType())||"41".equals(sendmailToCandidateReq.getType())){
            SendReexamineEmailReq sendReexamineEmailReq = new SendReexamineEmailReq();
            sendReexamineEmailReq.setResumeId(hrApplyFor.getResumeId());
            sendReexamineEmailReq.setStageName(sendmailToCandidateReq.getStageName());
            sendReexamineEmailReq.setNextStageName(sendmailToCandidateReq.getNextStageName());
            sendReexamineEmailReq.setInterviewTime(sendmailToCandidateReq.getInterviewTime());
            return mailService.sendReexamineEmail(sendReexamineEmailReq,request);
        }else if ("42".equals(sendmailToCandidateReq.getType())){
            log.info("发送offer模版邮件中。。。。");
            return ResultVO.failure(ResultEnum.FAILURE,msg,false);
        }
        return ResultVO.failure(ResultEnum.FAILURE,msg,false);
    }

    @Override
    public Map<String, Object> assignResume(AssignResumeReq assignResumeReq, HttpServletRequest request) {
        HrApplyFor hrApplyFor = new HrApplyFor();
        hrApplyFor.setApplyForId(assignResumeReq.getApplyForId());
        hrApplyFor.setResumeId(assignResumeReq.getResumeId());
        int a = hrApplyForMapper.updateByPrimaryKeySelective(hrApplyFor);
        HrRequestResume hrRequestResume = new HrRequestResume();
        hrRequestResume.setRequestId(assignResumeReq.getRequestId());
        hrRequestResume.setResumeId(assignResumeReq.getResumeId());
        hrRequestResume.setState("36");
        hrRequestResumeMapper.updateByPrimaryKeySelective(hrRequestResume);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public List<HrStageDetails> getHrStageDetails(GetHrStageDetailsReq getHrStageDetailsReq) {
        HrRecruitRequest hrRecruitRequest = hrRecruitRequestMapper.selectByPrimaryKey(getHrStageDetailsReq.getRequestId());
        List<HrStageDetails> hrStageDetails = hrStageDetailsMapper.selectByType(getHrStageDetailsReq);
        if ("1".equals(hrRecruitRequest.getInitiatorExamine())){
            for (int i = 0; i < hrStageDetails.size(); i++) {
                hrStageDetails.get(i).setInitiatorExamine("1");
            }
        }
        return hrStageDetails;
    }
}
