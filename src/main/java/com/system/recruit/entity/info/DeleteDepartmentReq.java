package com.system.recruit.entity.info;

import javax.validation.constraints.DecimalMin;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 23:07
 */
public class DeleteDepartmentReq {
    @DecimalMin(value = "1")
    private Integer departmentId;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
