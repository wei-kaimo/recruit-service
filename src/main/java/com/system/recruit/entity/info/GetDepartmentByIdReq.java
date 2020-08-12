package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/26 21:22
 */
public class GetDepartmentByIdReq {
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer departmentId;

    @Override
    public String toString() {
        return "GetDepartmentByIdReq{" +
                "departmentId=" + departmentId +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
