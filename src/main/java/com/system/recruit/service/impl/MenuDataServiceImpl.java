package com.system.recruit.service.impl;

import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.MenuUtils;
import com.system.recruit.dao.HrMenuMapper;
import com.system.recruit.entity.HrMenu;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.AddMenuReq;
import com.system.recruit.entity.info.DeleteMenuReq;
import com.system.recruit.entity.info.SortMenuReq;
import com.system.recruit.entity.info.UpdateMenuReq;
import com.system.recruit.service.MenuDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/7 0007 15:27
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class MenuDataServiceImpl implements MenuDataService {
    @Resource
    private HrMenuMapper hrMenuMapper;

    @Override
    public List<HrMenu> getMenuByRole(HrUser user) {
        String roleId = user.getRoleId();
        List<HrMenu> hrMenuList = hrMenuMapper.getMenuListByRoleId(Integer.valueOf(roleId));
        HrMenu hrMenuLRoot = hrMenuMapper.selectByPrimaryKey(0);
        HrMenu treeRoot = MenuUtils.treeRoot(hrMenuList,hrMenuLRoot);
        List<HrMenu> treeMenu = new ArrayList<>();
        treeMenu = treeRoot.getChildren();
        return treeMenu;
    }

    @Override
    public List<HrMenu> getAllRole() {
        List<HrMenu> hrMenuList = hrMenuMapper.selectAllMenu();
        /*HrMenu hrMenuLRoot = hrMenuMapper.selectByPrimaryKey(0);
        HrMenu treeRoot = MenuUtils.treeRoot(hrMenuList,hrMenuLRoot);
        List<HrMenu> treeMenu = new ArrayList<>();
        treeMenu = treeRoot.getChildren();*/
        return hrMenuList;
    }
    @Override
    public Map<String,Object> addMenu(AddMenuReq addMenuReq) {
        HrMenu addHrMenu = new HrMenu();
        List<HrMenu> brotherMenuList = hrMenuMapper.selectByParentId(addMenuReq.getParentId());
        Integer addMenuId = null;
        Integer addMenuSort = null;
        if (brotherMenuList.size()>0){
            addMenuId =  brotherMenuList.get(brotherMenuList.size()-1).getMenuId()+1;
            addMenuSort =  brotherMenuList.get(brotherMenuList.size()-1).getSort()+1;
        }else {
            addMenuId = addMenuReq.getParentId()*100+1;
            addMenuSort = addMenuId;
        }

        addHrMenu.setMenuId(addMenuId);
        addHrMenu.setMenuName(addMenuReq.getMenuName());
        addHrMenu.setUrl(addMenuReq.getUrl());
        addHrMenu.setParentId(addMenuReq.getParentId());
        addHrMenu.setSort(addMenuSort);
        addHrMenu.setRemark(addMenuReq.getRemark());
        addHrMenu.setIcon(addMenuReq.getIcon());
        addHrMenu.setType((String.valueOf(addMenuId).length()+1)/2);
        int a = hrMenuMapper.insertSelective(addHrMenu);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,addHrMenu,true);
    }

    @Override
    public Map<String, Object> deleteMenu(DeleteMenuReq deleteMenuReq) {
        int a = hrMenuMapper.deleteByKeyList(deleteMenuReq.getMenuId());
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> updateMenu(UpdateMenuReq updateMenuReq) {
        HrMenu menu = new HrMenu();
        menu.setMenuId(updateMenuReq.getMenuId());
        menu.setMenuName(updateMenuReq.getMenuName());
        menu.setUrl(updateMenuReq.getUrl());
        menu.setRemark(updateMenuReq.getRemark());
        menu.setIcon(updateMenuReq.getIcon());
        int a = hrMenuMapper.updateByPrimaryKeySelective(menu);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public Map<String, Object> sortMenu(SortMenuReq sortMenuReq) {
        List<HrMenu> brotherMenuList = hrMenuMapper.selectByParentId(sortMenuReq.getParentId());
        HrMenu affectMenu = null;
        HrMenu menu = null;
        for (int i = 0; i < brotherMenuList.size(); i++) {
            if (sortMenuReq.getType() == 0 && i==0){
                continue;
            }
            if (sortMenuReq.getType() == 1 && i==brotherMenuList.size()-1){
                continue;
            }
            menu = brotherMenuList.get(i);
            Integer medianSort = null;
            if (sortMenuReq.getMenuId().equals(menu.getMenuId())){
                if (sortMenuReq.getType() == 0){
                    affectMenu = brotherMenuList.get(i-1);
                }
                if (sortMenuReq.getType() == 1){
                    affectMenu = brotherMenuList.get(i+1);
                }
                medianSort =affectMenu.getSort();
                affectMenu.setSort(menu.getSort());
                menu.setSort(medianSort);
                break;
            }
        }
        if (affectMenu==null){
            return ResultVO.failure(ResultEnum.FAILURE,"超出范围",false);
        }else {
            int a = hrMenuMapper.updateByPrimaryKeySelective(affectMenu);
            int b = hrMenuMapper.updateByPrimaryKeySelective(menu);
        }
        return ResultVO.result(ResultEnum.SUCCESS,true);
    }
}
