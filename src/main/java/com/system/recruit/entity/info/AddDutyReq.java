package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 23:55
 */
public class AddDutyReq {
    @NotEmpty(message = ErrorMsg.SOS00000001)
    private String dutyName;
    @NotNull(message = ErrorMsg.SOS00000001)
    @DecimalMin(value = "0")
    private Integer departmentId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String dutyHierarchy;
    @NotEmpty(message = ErrorMsg.SOS00000001)
    private String dutyDesctibe;

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDutyHierarchy() {
        return dutyHierarchy;
    }

    public void setDutyHierarchy(String dutyHierarchy) {
        this.dutyHierarchy = dutyHierarchy;
    }

    public String getDutyDesctibe() {
        return dutyDesctibe;
    }

    public void setDutyDesctibe(String dutyDesctibe) {
        this.dutyDesctibe = dutyDesctibe;
    }
}
