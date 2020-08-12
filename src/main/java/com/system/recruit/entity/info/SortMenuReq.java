package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/8 0008 19:05
 */
public class SortMenuReq {
    @DecimalMin(value = "1")
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer menuId;
    @DecimalMin(value = "0")
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer parentId;
    @DecimalMax(value = "1")
    @NotNull(message = ErrorMsg.SOS00000001)
    private int type;//0提升排序  1下降排序

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
