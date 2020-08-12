package com.system.recruit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.RoleMenuUtils;
import com.system.recruit.dao.HrMenuMapper;
import com.system.recruit.dao.HrRoleMapper;
import com.system.recruit.dao.HrRoleMenuMapper;
import com.system.recruit.entity.HrRole;
import com.system.recruit.entity.HrRoleMenu;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.RoleDataService;
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
 * @date 2020/5/12 0012 21:13
 */
@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class RoleDataServiceIml implements RoleDataService {
    @Resource
    private HrRoleMapper hrRoleMapper;
    @Resource
    private HrRoleMenuMapper hrRoleMenuMapper;
    @Resource
    private HrMenuMapper hrMenuMapper;

    @Override
    public GetAllRoleResp getAllRole(GetAllRoleReq getAllRoleReq) {
        GetAllRoleResp getAllRoleResp = new GetAllRoleResp();
        PageHelper.startPage(Integer.parseInt(getAllRoleReq.getPageNum()), Integer.parseInt(getAllRoleReq.getPageSize()));
        Page<HrRole> hrRoles = hrRoleMapper.selectAllRole();
        long totalNum = hrRoles.getTotal();
        getAllRoleResp.setCount(String.valueOf(totalNum));
        getAllRoleResp.setHrRoleList(hrRoles.getResult());
        return getAllRoleResp;
    }

    @Override
    public Map<String, Object> setRoleMenu(SetRoleMenuReq setRoleMenuReq) {
        int a = hrRoleMenuMapper.deleteByRoleId(setRoleMenuReq.getRoleId());
        int b = 0;
        List<HrRoleMenu> hrRoleMenus = new ArrayList<>();
        List<Integer> menuIdList = setRoleMenuReq.getMenuIds();
        for (Integer menuId : menuIdList) {
            HrRoleMenu hrRoleMenu = new HrRoleMenu();
            hrRoleMenu.setRoleId(setRoleMenuReq.getRoleId());
            hrRoleMenu.setMeunId(menuId);
            hrRoleMenus.add(hrRoleMenu);
        }
        b = hrRoleMenuMapper.insertList(hrRoleMenus);
        if (b>0){
            return ResultVO.result(ResultEnum.SUCCESS,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"角色赋权失败！",false);
        }
    }

    @Override
    public Map<String, Object> addAllRole(AddAllRoleReq addAllRoleReq) {
        HrRole hrRole = new HrRole();
        hrRole.setRoleId(addAllRoleReq.getRoleId());
        hrRole.setRole(addAllRoleReq.getRole());
        hrRole.setRoleName(addAllRoleReq.getRoleName());
        hrRole.setDesctibe(addAllRoleReq.getDesctibe());
        int a = hrRoleMapper.insertSelective(hrRole);
        if (a>0){
            return ResultVO.result(ResultEnum.SUCCESS,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"新建角色失败！",false);
        }
    }

    @Override
    public List<HrRole> getRole() {
        List<HrRole> hrRoles = hrRoleMapper.selectRole();
        return hrRoles;
    }

    @Override
    public List<GetRoleMenuResp> getRoleMenu(GetRoleMenuReq getRoleMenuReq) {
        List<Integer> menuIdList = hrRoleMenuMapper.selectByRoleId(getRoleMenuReq.getRoleId());
        List<GetRoleMenuResp> hrMenuList = hrMenuMapper.selectAllRoleMenu();
        for (int i = 0; i < hrMenuList.size(); i++) {
            for (Integer menuId : menuIdList) {
                if (menuId.equals(hrMenuList.get(i).getMenuId())){
                    hrMenuList.get(i).setFlag("1");
                }
            }
        }
        GetRoleMenuResp hrMenuLRoot = hrMenuMapper.selectRoleMenuByKey(0);
        GetRoleMenuResp treeRoot = RoleMenuUtils.treeRoot(hrMenuList,hrMenuLRoot);
        List<GetRoleMenuResp> treeRoleMenu = new ArrayList<>();
        treeRoleMenu = treeRoot.getChildren();
        recursive(treeRoleMenu);
        return treeRoleMenu;
    }

    @Override
    public Map<String, Object> deleteRole(DeleteRole deleteRole) {
        hrRoleMapper.deleteByPrimaryKey(deleteRole.getRoleId());
        hrRoleMenuMapper.deleteByRoleId(deleteRole.getRoleId());
        return ResultVO.result(ResultEnum.SUCCESS,true);
    }

    private void recursive (List<GetRoleMenuResp> list){
        if (list != null && list.size()>0){
            for (GetRoleMenuResp getRoleMenuResp : list) {
                if ("1".equals(getRoleMenuResp.getFlag()) && getRoleMenuResp.getChildren() != null && getRoleMenuResp.getChildren().size()>1){
                    getRoleMenuResp.setFlag("0");
                    recursive(getRoleMenuResp.getChildren());
                }
            }
        }

    };
}
