package com.system.recruit.service;

import com.system.recruit.entity.HrDuty;
import com.system.recruit.entity.info.AddDutyReq;
import com.system.recruit.entity.info.DeleteDutyReq;
import com.system.recruit.entity.info.GetAllDutyByDepartmentIdReq;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 23:38
 */
public interface DutyDataService {
    List<HrDuty> getAllDutyByRole(HttpServletRequest request);

    Map<String, Object> addDuty(AddDutyReq addAllRoleReq);

    List<HrDuty> getAllDutyByDepartmentId(GetAllDutyByDepartmentIdReq getAllDutyByDepartmentIdReq);

    Map<String, Object> deleteDuty(DeleteDutyReq deleteDutyReq);
}
