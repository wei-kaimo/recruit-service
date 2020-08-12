package com.system.recruit.service.impl;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.DepartmentsUtils;
import com.system.recruit.dao.HrDepartmentMapper;
import com.system.recruit.entity.HrDepartment;
import com.system.recruit.entity.info.AddDepartmentReq;
import com.system.recruit.entity.info.DeleteDepartmentReq;
import com.system.recruit.entity.info.GetDepartmentByIdReq;
import com.system.recruit.service.DepartmentDataService;
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
 * @date 2020/5/12 0012 22:20
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class DepartmentDataServiceImpl implements DepartmentDataService {
    @Resource
    private HrDepartmentMapper hrDepartmentMapper;


    @Override
    public List<HrDepartment> getAllDepartmentTree(HttpServletRequest request) {
        List<HrDepartment> hrDepartments = hrDepartmentMapper.selectAllDepartment();
        HrDepartment hrDepartmentRoot = hrDepartmentMapper.selectByPrimaryKey(0);
        HrDepartment treeRoot = DepartmentsUtils.treeRoot(hrDepartments,hrDepartmentRoot);
        List<HrDepartment> treeDepartment = treeRoot.getChildren();
        return treeDepartment;
    }
    @Override
    public List<HrDepartment> getAllDepartment(HttpServletRequest request) {
        List<HrDepartment> hrDepartments = hrDepartmentMapper.selectAllDepartment();
        /*HrDepartment hrDepartmentRoot = hrDepartmentMapper.selectByPrimaryKey(0);
        HrDepartment treeRoot = DepartmentsUtils.treeRoot(hrDepartments,hrDepartmentRoot);
        List<HrDepartment> treeDepartment = treeRoot.getChildren();*/
        int i = 0 ;
        for (HrDepartment hrDepartment : hrDepartments) {

            if ("0".equals(hrDepartment.getDepartmentId())){
                hrDepartments.remove(i);
            }
            i++;
        }
        return hrDepartments;
    }

    @Override
    public HrDepartment getDepartmentById(GetDepartmentByIdReq getDepartmentByIdReq) {
        return hrDepartmentMapper.selectByPrimaryKey(getDepartmentByIdReq.getDepartmentId());
    }

    @Override
    public Map<String,Object> addDepartment(AddDepartmentReq addDepartmentReq) {
        HrDepartment hrDepartment = new HrDepartment();
        List<HrDepartment> brotherMenuList = hrDepartmentMapper.selectByParentId(addDepartmentReq.getParentDepartmentId());
        Integer addDepartmentId = null;
        if (brotherMenuList.size()>0){
            addDepartmentId =  brotherMenuList.get(brotherMenuList.size()-1).getDepartmentId()+1;
        }else {
            addDepartmentId = addDepartmentReq.getParentDepartmentId()*100+1;
        }

        hrDepartment.setDepartmentId(addDepartmentId);
        hrDepartment.setDepartmentName(addDepartmentReq.getDepartmentName());
        hrDepartment.setDepartmentDesctibe(addDepartmentReq.getDepartmentDesctibe());
        hrDepartment.setParentDepartmentId(addDepartmentReq.getParentDepartmentId());
        int a = hrDepartmentMapper.insertSelective(hrDepartment);
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> deleteDepartment(DeleteDepartmentReq deleteDepartmentReq) {
        int a = hrDepartmentMapper.deleteByPrimaryKey(deleteDepartmentReq.getDepartmentId());
        if (a<0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }
}
