package com.system.recruit.common.config;

import com.system.recruit.StartUp;
import com.system.recruit.dao.HrRecruitRequestMapper;
import com.system.recruit.entity.HrRecruitRequest;
import com.system.recruit.service.RecruitApplyForDataService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/15 0015 11:56
 */
@Component
@Aspect
@Slf4j
@Transactional
public class WebControllerAop {
    @Resource
    private StartUp startUp;
    @Resource
    private RecruitApplyForDataService recruitApplyForDataService;
    @Resource
    private HrRecruitRequestMapper hrRecruitRequestMapper;

    @Pointcut("execution(* AOP*(..))")
    public  void creatorService(){}

    @Pointcut("execution(* com.system.recruit.controller.RecruitReqDataController.approvalRecruitReqFlow(..))")
    public  void generateRecruitApplyForService(){}

    @Pointcut("execution(* com.system.recruit.controller.RecruitReqDataController.initiateRecruitReq(..))")
    public  void initiateRecruitReqService(){}

    @Pointcut("execution(* com.system.recruit.controller.RecruitApplyForDataController.approveHrStageDetails(..))")
    public  void approveHrStageDetails(){}

    @AfterReturning(pointcut = "creatorService()")
    private void refreshUserMsg(){
        startUp.refreshUserMsg();
    }

    @AfterReturning(pointcut = "generateRecruitApplyForService()",returning="result")
    private void generateRecruitApplyFor(JoinPoint joinPoint, Map<String,Object> result){
        Integer code = (Integer) result.get("code");
        if (101==code){
            if (result.get("data") instanceof HrRecruitRequest){
                HrRecruitRequest hrRecruitRequest = (HrRecruitRequest) result.get("data");
                hrRecruitRequest.setFirst(true);
                recruitApplyForDataService.generateRecruitApplyFor(hrRecruitRequest);
                hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
                //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
    }


    @AfterReturning(pointcut = "approveHrStageDetails()",returning="result")
    private void approveHrStageDetailsAOP(JoinPoint joinPoint, Map<String,Object> result){
        Integer code = (Integer) result.get("code");
        if (101==code){
            if (result.get("data") instanceof HrRecruitRequest){
                HrRecruitRequest hrRecruitRequest = (HrRecruitRequest) result.get("data");
                recruitApplyForDataService.generateRecruitApplyFor(hrRecruitRequest);
            }
        }

    }

    @AfterReturning(pointcut = "initiateRecruitReqService()",returning="result")
    private void initiateRecruitReqService(JoinPoint joinPoint, Map<String,Object> result){
        Integer code = (Integer) result.get("code");
        if (101==code){
            if (result.get("data") instanceof HrRecruitRequest){
                HrRecruitRequest hrRecruitRequest = (HrRecruitRequest) result.get("data");
                hrRecruitRequest.setFirst(true);
                recruitApplyForDataService.generateRecruitApplyFor(hrRecruitRequest);
                hrRecruitRequestMapper.updateByPrimaryKeySelective(hrRecruitRequest);
                //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
    }
}
