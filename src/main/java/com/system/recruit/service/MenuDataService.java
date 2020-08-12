package com.system.recruit.service;

import com.system.recruit.entity.HrMenu;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.AddMenuReq;
import com.system.recruit.entity.info.DeleteMenuReq;
import com.system.recruit.entity.info.SortMenuReq;
import com.system.recruit.entity.info.UpdateMenuReq;

import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/7 0007 15:20
 */
public interface MenuDataService {
    List<HrMenu> getMenuByRole(HrUser user);
    List<HrMenu> getAllRole();
    Map<String,Object> addMenu(AddMenuReq addMenuReq);
    Map<String,Object> deleteMenu(DeleteMenuReq deleteMenuReq);
    Map<String,Object> updateMenu(UpdateMenuReq updateMenuReq);
    Map<String,Object> sortMenu(SortMenuReq sortMenuReq);
}
