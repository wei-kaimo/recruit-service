package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 22:52
 */
public class AddDepartmentReq {
/*    @DecimalMin(value = "1")
    private Integer departmentId;*/
    @NotEmpty(message = ErrorMsg.SOS00000001)
    private String departmentName;
    @DecimalMin(value = "0")
    private Integer parentDepartmentId;

    private String departmentDesctibe;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Integer getParentDepartmentId() {
        return parentDepartmentId;
    }

    public void setParentDepartmentId(Integer parentDepartmentId) {
        this.parentDepartmentId = parentDepartmentId;
    }

    public String getDepartmentDesctibe() {
        return departmentDesctibe;
    }

    public void setDepartmentDesctibe(String departmentDesctibe) {
        this.departmentDesctibe = departmentDesctibe;
    }
}
