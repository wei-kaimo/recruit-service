package com.system.recruit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.dao.HrConfigMapper;
import com.system.recruit.entity.HrConfig;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.ConfigDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/9 0009 15:16
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class ConfigDataServiceImpl implements ConfigDataService {
    @Resource
    private HrConfigMapper hrConfigMapper;

    @Override
    public GetAllConfigResp getAllConfig(GetAllConfigReq getAllConfigReq) {
        GetAllConfigResp getAllConfigResp = new GetAllConfigResp();
        PageHelper.startPage(Integer.parseInt(getAllConfigReq.getPageNum()), Integer.parseInt(getAllConfigReq.getPageSize()));
        Page<HrConfig> hrConfigs = hrConfigMapper.selectAll();
        long totalNum = hrConfigs.getTotal();
        getAllConfigResp.setCount(String.valueOf(totalNum));
        getAllConfigResp.setHrConfigList(hrConfigs.getResult());
        return getAllConfigResp;
    }

    @Override
    public Map<String, Object> addConfig(AddConfigReq addConfig) {
        HrConfig hrConfig = new HrConfig();
        hrConfig.setScope(Integer.valueOf(addConfig.getScope()));
        hrConfig.setConfigName(addConfig.getConfigName());
        hrConfig.setConfigType(addConfig.getConfigType());
        int a = hrConfigMapper.insertHrConfig(hrConfig);
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> deleteConfig(DeleteConfigReq deleteConfigReq) {
        int a = hrConfigMapper.deleteByKey(Integer.valueOf(deleteConfigReq.getConfigId()));
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> updateConfig(UpdateConfigReq updateConfigReq) {
        HrConfig hrConfig = new HrConfig();
        hrConfig.setScope(Integer.valueOf(updateConfigReq.getScope()));
        hrConfig.setConfigType(updateConfigReq.getConfigType());
        hrConfig.setConfigName(updateConfigReq.getConfigName());
        int a = hrConfigMapper.updateByPrimaryKeySelective(hrConfig);
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public List<HrConfig> getConfigByType(GetConfigByTypeReq getConfigByTypeReq) {
        List<HrConfig> hrConfigs = hrConfigMapper.selectByType(getConfigByTypeReq.getConfigType(),getConfigByTypeReq.getScope());
        return hrConfigs;
    }

    @Override
    public HrConfig getConfigById(GetConfigByIdReq getAllConfigReq) {
        return hrConfigMapper.selectByPrimaryKey(getAllConfigReq.getConfigId());
    }
}
