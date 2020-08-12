package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/8 0008 18:47
 */
public class UpdateMenuReq {
    @DecimalMax(value = "0")
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer sign;

    @DecimalMin(value = "1")
    @NotNull(message = ErrorMsg.SOS00000001)
    private Integer menuId;

    private String menuName;

    private String url;

    private Integer sort;

    private String remark;

    private String icon;

    public Integer getSign() {
        return sign;
    }

    public void setSign(Integer sign) {
        this.sign = sign;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
