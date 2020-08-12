package com.system.recruit.entity.info;

import javax.validation.constraints.DecimalMin;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/11 0011 16:00
 */
public class DeleteUserParticularsReq {
    @DecimalMin(value = "0")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
