package com.system.recruit.service.impl;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.dao.HrFlowConfigMapper;
import com.system.recruit.entity.HrFlowConfig;
import com.system.recruit.entity.info.AddFlowConfigReq;
import com.system.recruit.entity.info.DeleteFlowConfigReq;
import com.system.recruit.entity.info.GetFlowConfigReq;
import com.system.recruit.entity.info.SortFlowConfigReq;
import com.system.recruit.service.FlowConfigDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:37
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class FlowConfigDataServiceImpl implements FlowConfigDataService {
    @Resource
    private HrFlowConfigMapper hrFlowConfigMapper;
    @Override
    public List<HrFlowConfig> getFlowConfig(GetFlowConfigReq getFlowConfigReq) {
        List<HrFlowConfig> hrFlowConfigs = hrFlowConfigMapper.selectByPositionIdStageType(getFlowConfigReq.getPositionId(),getFlowConfigReq.getType());
        return hrFlowConfigs;
    }

    @Override
    public Map<String,Object> addFlowConfig(AddFlowConfigReq addFlowConfigReq) {
        HrFlowConfig hrFlowConfig = new HrFlowConfig();
        hrFlowConfig.setStageId(UUID.randomUUID().toString());
        hrFlowConfig.setPositionId(addFlowConfigReq.getPositionId());
        hrFlowConfig.setStageName(addFlowConfigReq.getStageName());
        hrFlowConfig.setStageType(addFlowConfigReq.getStageType());
        hrFlowConfig.setOperationType(addFlowConfigReq.getOperationType());
        List<HrFlowConfig> hrFlowConfigs = hrFlowConfigMapper.selectByPositionIdStageType(addFlowConfigReq.getPositionId(),addFlowConfigReq.getStageType());
        if (hrFlowConfigs.size()==0){
            if ("12".equals(addFlowConfigReq.getStageType())){
                hrFlowConfig.setSort(Integer.valueOf("1"));
            }
            if ("13".equals(addFlowConfigReq.getStageType())){
                hrFlowConfig.setSort(Integer.valueOf("100"+"001"));
            }
            if ("14".equals(addFlowConfigReq.getStageType())){
                hrFlowConfig.setSort(Integer.valueOf("100100"+"001"));
            }
        }else {
            hrFlowConfig.setSort(hrFlowConfigs.get(hrFlowConfigs.size()-1).getSort()+1);
        }
        int a = hrFlowConfigMapper.insertSelective(hrFlowConfig);
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> deleteFlowConfig(DeleteFlowConfigReq deleteFlowConfigReq) {
        int a = hrFlowConfigMapper.deleteByPrimaryKey(deleteFlowConfigReq.getStageId());
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }
    @Override
    public Map<String, Object> sortFlowConfig(SortFlowConfigReq sortFlowConfigReq) {
        List<HrFlowConfig> hrFlowConfigs = hrFlowConfigMapper.selectByPositionIdStageType(sortFlowConfigReq.getPositionId(),sortFlowConfigReq.getStageType());
        HrFlowConfig affectFlowConfig = null;
        HrFlowConfig flowConfig = null;
        for (int i = 0; i < hrFlowConfigs.size(); i++) {
            if (sortFlowConfigReq.getType() == 0 && i==0){
                continue;
            }
            if (sortFlowConfigReq.getType() == 1 && i==hrFlowConfigs.size()-1){
                continue;
            }
            flowConfig = hrFlowConfigs.get(i);
            Integer medianSort = null;
            if (sortFlowConfigReq.getStageId().equals(flowConfig.getStageId())){
                if (sortFlowConfigReq.getType() == 0){
                    affectFlowConfig = hrFlowConfigs.get(i-1);
                }
                if (sortFlowConfigReq.getType() == 1){
                    affectFlowConfig = hrFlowConfigs.get(i+1);
                }
                medianSort =affectFlowConfig.getSort();
                affectFlowConfig.setSort(flowConfig.getSort());
                flowConfig.setSort(medianSort);
                break;
            }
        }
        if (affectFlowConfig==null){
            return ResultVO.failure(ResultEnum.FAILURE,"超出范围",false);
        }else {
            int a = hrFlowConfigMapper.updateByPrimaryKeySelective(affectFlowConfig);
            int b = hrFlowConfigMapper.updateByPrimaryKeySelective(flowConfig);
        }
        return ResultVO.result(ResultEnum.SUCCESS,true);
    }
}
