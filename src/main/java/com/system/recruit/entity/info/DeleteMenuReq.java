package com.system.recruit.entity.info;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/8 0008 18:47
 */
public class DeleteMenuReq {
    @NotNull
    private List<Integer> menuId;

    public List<Integer> getMenuId() {
        return menuId;
    }

    public void setMenuId(List<Integer> menuId) {
        this.menuId = menuId;
    }
}
