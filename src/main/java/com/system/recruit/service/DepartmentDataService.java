package com.system.recruit.service;

import com.system.recruit.entity.HrDepartment;
import com.system.recruit.entity.info.AddDepartmentReq;
import com.system.recruit.entity.info.DeleteDepartmentReq;
import com.system.recruit.entity.info.GetDepartmentByIdReq;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 22:20
 */
public interface DepartmentDataService {
    List<HrDepartment> getAllDepartment(HttpServletRequest request);
    List<HrDepartment> getAllDepartmentTree(HttpServletRequest request);
    Map<String,Object> addDepartment(AddDepartmentReq addDepartmentReq);
    Map<String, Object> deleteDepartment(DeleteDepartmentReq deleteDepartmentReq);

    HrDepartment getDepartmentById(GetDepartmentByIdReq getDepartmentByIdReq);
}
