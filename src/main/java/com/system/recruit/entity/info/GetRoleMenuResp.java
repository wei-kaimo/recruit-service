package com.system.recruit.entity.info;

import java.util.ArrayList;
import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/21 0021 11:08
 */
public class GetRoleMenuResp {
    private Integer menuId;

    private String menuName;

    private Integer parentId;

    private String flag = "0";

    private List<GetRoleMenuResp> children = new ArrayList<>();

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<GetRoleMenuResp> getChildren() {
        return children;
    }

    public void setChildren(List<GetRoleMenuResp> children) {
        this.children = children;
    }
}
