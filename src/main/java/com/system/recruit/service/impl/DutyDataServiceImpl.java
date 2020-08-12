package com.system.recruit.service.impl;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.dao.HrDepartmentMapper;
import com.system.recruit.dao.HrDutyMapper;
import com.system.recruit.dao.HrUserDepartmentMapper;
import com.system.recruit.entity.HrDepartment;
import com.system.recruit.entity.HrDuty;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.HrUserDepartment;
import com.system.recruit.entity.info.AddDutyReq;
import com.system.recruit.entity.info.DeleteDutyReq;
import com.system.recruit.entity.info.GetAllDutyByDepartmentIdReq;
import com.system.recruit.service.DutyDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 23:38
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class DutyDataServiceImpl implements DutyDataService {
    @Resource
    private GetUserDateUtil getUserDateUtil;
    @Resource
    private HrUserDepartmentMapper hrUserDepartmentMapper;
    @Resource
    private HrDutyMapper hrDutyMapper;
    @Resource
    private HrDepartmentMapper hrDepartmentMapper;


    @Override
    public List<HrDuty> getAllDutyByRole(HttpServletRequest request) {
        HrUser user = getUserDateUtil.getUserDateInternalUtil(request);
        Integer userId = user.getUserId();
        List<HrDuty> hrDuties = null;
        if ("1".equals(user.getRoleId()) || "2".equals(user.getRoleId()) || "7".equals(user.getRoleId()) || "8".equals(user.getRoleId())){
            hrDuties = hrDutyMapper.selectAllHrDuty();
        }else {
            HrUserDepartment hrUserDepartment = hrUserDepartmentMapper.selectById(userId);
            hrDuties = hrDutyMapper.selectByDepartmentId(hrUserDepartment.getDepartmentId());
        }
        return hrDuties;
    }

    @Override
    public Map<String, Object> addDuty(AddDutyReq addAllRoleReq) {
        Integer dutyHierarchy = 0;
        HrDepartment hrDepartment = hrDepartmentMapper.selectByPrimaryKey(addAllRoleReq.getDepartmentId());
        int i = 1;
        while (0 != hrDepartment.getParentDepartmentId()){
            hrDepartment = hrDepartmentMapper.selectByPrimaryKey(hrDepartment.getParentDepartmentId());
            i++;
        }
        dutyHierarchy = Integer.valueOf((i*100)+addAllRoleReq.getDutyHierarchy()) ;

        HrDuty hrDuty = new HrDuty();
        hrDuty.setDepartmentId(addAllRoleReq.getDepartmentId());
        hrDuty.setDutyDesctibe(addAllRoleReq.getDutyDesctibe());
        hrDuty.setDutyHierarchy(dutyHierarchy);
        hrDuty.setDutyName(addAllRoleReq.getDutyName());
        int a = hrDutyMapper.insertSelective(hrDuty);
        //int a =0;
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public List<HrDuty> getAllDutyByDepartmentId(GetAllDutyByDepartmentIdReq getAllDutyByDepartmentIdReq) {
        return hrDutyMapper.selectByDepartmentId(getAllDutyByDepartmentIdReq.getDepartmentId());
    }

    @Override
    public Map<String, Object> deleteDuty(DeleteDutyReq deleteDutyReq) {
        int a = hrDutyMapper.deleteByPrimaryKey(deleteDutyReq.getDutyId());
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }
}
