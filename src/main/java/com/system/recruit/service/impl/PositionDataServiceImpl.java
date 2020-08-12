package com.system.recruit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Constant;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.dao.HrFlowConfigMapper;
import com.system.recruit.dao.HrPositionMapper;
import com.system.recruit.entity.HrFlowConfig;
import com.system.recruit.entity.HrPosition;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.PositionDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 19:53
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class PositionDataServiceImpl implements PositionDataService {
    @Resource
    private HrPositionMapper hrPositionMapper;
    @Resource
    private HrFlowConfigMapper hrFlowConfigMapper;
    @Override
    public List<HrPosition> getAllPosition(GetAllPositionReq getAllPositionReq) {
        List<HrPosition> hrPositionList = new ArrayList<>();
        if (getAllPositionReq.getDepartmentId() != null && !"".equals(getAllPositionReq.getDepartmentId())){
            hrPositionList = hrPositionMapper.selectByDepartmentId(getAllPositionReq.getDepartmentId());
        }else {
            hrPositionList = hrPositionMapper.selectAll();
        }
        return hrPositionList;
    }

    @Override
    public Map<String, Object> addPosition(AddPositionReq addPositionReq) {
        Object positionIdObj = "";
        HrPosition hrPosition = new HrPosition();
        String positionId = UUID.randomUUID().toString();
        positionIdObj = positionId;
        hrPosition.setPositionId(positionId);
        hrPosition.setPositionName(addPositionReq.getPositionName());
        hrPosition.setDepartmentId(addPositionReq.getDepartmentId());
        hrPosition.setCityName(addPositionReq.getCityName());
        hrPosition.setJobNature(addPositionReq.getJobNature());
        hrPosition.setPositionRequirements(addPositionReq.getPositionRequirements());
        hrPosition.setPositionResponsibilities(addPositionReq.getPositionResponsibilities());
        int a = hrPositionMapper.insertSelective(hrPosition);
        log.info(Constant.SYS_CONFIG.get("general_recruitment_process"));
        List<HrFlowConfig> hrFlowConfigs = JSONObject.parseArray(Constant.SYS_CONFIG.get("general_recruitment_process"), HrFlowConfig.class);
        log.info(hrFlowConfigs.toString());
        for (HrFlowConfig hrFlowConfig : hrFlowConfigs) {
            hrFlowConfig.setStageId(UUID.randomUUID().toString());
            hrFlowConfig.setPositionId(positionId);
            hrFlowConfigMapper.insertSelective(hrFlowConfig);
        }
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,positionIdObj,true);
    }

    @Override
    public Map<String, Object> updatePosition(UpdatePositionReq updatePositionReq) {
        HrPosition hrPosition = new HrPosition();
        hrPosition.setPositionId(updatePositionReq.getPositionId());
        hrPosition.setPositionName(updatePositionReq.getPositionName());
        hrPosition.setDepartmentId(updatePositionReq.getDepartmentId());
        hrPosition.setCityName(updatePositionReq.getCityName());
        hrPosition.setJobNature(updatePositionReq.getJobNature());
        hrPosition.setPositionResponsibilities(updatePositionReq.getPositionResponsibilities());
        hrPosition.setPositionRequirements(updatePositionReq.getPositionRequirements());
        int a = hrPositionMapper.updateByPrimaryKeySelective(hrPosition);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> deletePosition(DeletePositionReq deletePositionReq) {
        int a = hrPositionMapper.deleteByPrimaryKey(deletePositionReq.getPositionId());
        int b = 0;
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }else {
            b = hrFlowConfigMapper.deleteByPosition(deletePositionReq.getPositionId());
            return ResultVO.result(ResultEnum.SUCCESS,b,true);
        }
    }

    @Override
    public GetPositionListResp getPositionList(GetPositionListReq getPositionListReq) {
        GetPositionListResp getPositionListResp = new GetPositionListResp();
        List<HrPositionResp> hrPositionRespList = new ArrayList<>();
        PageHelper.startPage(Integer.parseInt(getPositionListReq.getPageNum()), Integer.parseInt(getPositionListReq.getPageSize()));
        Page<HrPosition> hrPositions = hrPositionMapper.selectAllByPage();
        List<HrPosition> hrPositionList = hrPositions.getResult();
        for (HrPosition hrPosition : hrPositionList) {
            HrPositionResp hrPositionResp = new HrPositionResp();
            hrPositionResp.setPositionId(hrPosition.getPositionId());
            hrPositionResp.setPositionName(hrPosition.getPositionName());
            hrPositionResp.setDepartmentId(hrPosition.getDepartmentId());
            hrPositionResp.setCityName(hrPosition.getCityName());
            hrPositionResp.setJobNature(hrPosition.getJobNature());
            hrPositionResp.setPositionRequirements(hrPosition.getPositionRequirements());
            hrPositionResp.setPositionResponsibilities(hrPosition.getPositionResponsibilities());
            hrPositionResp.setCreateDate(hrPosition.getCreateDate());
            hrPositionResp.setUpdateDate(hrPosition.getUpdateDate());
            if ("1".equals(getPositionListReq.getType())){
                hrPositionResp.setFlowConfigList(hrFlowConfigMapper.selectByPositionId(hrPosition.getPositionId()));
            }
            hrPositionRespList.add(hrPositionResp);
        }
        getPositionListResp.setHrPositions(hrPositionRespList);
        getPositionListResp.setCount(String.valueOf(hrPositions.getTotal()));
        return getPositionListResp;
    }

    @Override
    public HrPosition getPositionById(GetPositionByIdReq getAllPositionReq) {
        return hrPositionMapper.selectByPrimaryKey(getAllPositionReq.getPositionId());
    }
}
