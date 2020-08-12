package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/8 0008 16:37
 */
public class AddMenuReq {
    private Integer Type;

    private Integer menuId;

    @NotEmpty(message = ErrorMsg.SOS00000001)
    private String menuName;

    @NotEmpty(message = ErrorMsg.SOS00000001)
    private String url;

    @DecimalMin(value = "0")
    private Integer parentId;

    private Integer sort;

    private String remark;

    private String icon;

    public Integer getType() {
        return Type;
    }

    public void setType(Integer type) {
        Type = type;
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
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
