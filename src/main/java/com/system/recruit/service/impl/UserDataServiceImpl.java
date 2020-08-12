package com.system.recruit.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.dao.HrUserDepartmentMapper;
import com.system.recruit.dao.HrUserMapper;
import com.system.recruit.dao.HrUserRoleMapper;
import com.system.recruit.entity.*;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.UserDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class UserDataServiceImpl implements UserDataService {

    @Resource
    private GetUserDateUtil getUserDateUtil;
    @Resource
    private HrUserMapper hrUserMapper;
    @Resource
    private HrUserDepartmentMapper hrUserDepartmentMapper;
    @Resource
    private HrUserRoleMapper hrUserRoleMapper;


    @Override
    //@Transactional(rollbackFor=Exception.class)
    public UserResp getCurrentUser(HttpServletRequest request) {
        return getUserDateUtil.getUserDateExternalUtil(request);
    }

    @Override
    public GetAllUserResp getAllUser(GetAllUserReq getAllUserReq) {
        GetAllUserResp getAllUserResp = new GetAllUserResp();
        PageHelper.startPage(Integer.parseInt(getAllUserReq.getPageNum()), Integer.parseInt(getAllUserReq.getPageSize()));
        Page<UserOutline> hrUsers = hrUserMapper.getUserOutline();
        long totalNum = hrUsers.getTotal();
        getAllUserResp.setCount(String.valueOf(totalNum));
        getAllUserResp.setHrUserList(hrUsers.getResult());
        return getAllUserResp;
    }

    @Override
    public List<UserResp> getUserByRoleId(GetUserByRoleIdReq getUserByRoleIdReq) {

        List<UserResp> userRespList = hrUserMapper.gerUserListByRoleId(getUserByRoleIdReq.getRoleIds());
        return userRespList;
    }

    @Override
    public UserParticulars getUserParticulars(GetUserParticularsReq getUserParticularsReq) {
        UserParticulars userParticulars = hrUserMapper.getUserParticulars(getUserParticularsReq.getUserId());
        return userParticulars;
    }

    @Override
    public Map<String, Object> updateUserParticulars(UpdateUserParticularsReq updateUserParticularsReq) {
        HrUser hrUser = new HrUser();
        hrUser.setUserId(updateUserParticularsReq.getUserId());
        hrUser.setUserRealName(updateUserParticularsReq.getUserRealName());
        hrUser.setUserPhone(updateUserParticularsReq.getUserPhone());
        hrUser.setUserQQ(updateUserParticularsReq.getUserQQ());
        hrUser.setUserEmail(updateUserParticularsReq.getUserEmail());
        hrUser.setUserSex(updateUserParticularsReq.getUserSex());
        hrUser.setUserAddress(updateUserParticularsReq.getUserAddress());
        int a = hrUserMapper.updateByPrimaryKeySelective(hrUser);
        int b = 0;
        int c = 0;
        if (a>0){
            HrUserDepartment hrUserDepartment = new HrUserDepartment();
            hrUserDepartment.setUserId(updateUserParticularsReq.getUserId());
            hrUserDepartment.setDepartmentId(updateUserParticularsReq.getDepartmentId());
            hrUserDepartment.setDutyId(updateUserParticularsReq.getDutyId());
            b = hrUserDepartmentMapper.updateSelective(hrUserDepartment);

            HrUserRole hrUserRole = new HrUserRole();
            hrUserRole.setUserId(updateUserParticularsReq.getUserId());
            hrUserRole.setRoleId(updateUserParticularsReq.getRoleId());
            c = hrUserRoleMapper.updateUserRole(hrUserRole);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户信息更新失败！",false);
        }

        if (b>0 && c>0){
            return ResultVO.result(ResultEnum.SUCCESS,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户权限信息更新失败！",false);
        }
    }

    @Override
    public Map<String, Object> updateCurrentUser(UpdateCurrentUserReq updateCurrentUserReq) {
        HrUser hrUser = new HrUser();
        hrUser.setUserId(updateCurrentUserReq.getUserId());
        hrUser.setUserPwd(updateCurrentUserReq.getUserPwd());
        hrUser.setUserRealName(updateCurrentUserReq.getUserRealName());
        hrUser.setUserPhone(updateCurrentUserReq.getUserPhone());
        hrUser.setUserQQ(updateCurrentUserReq.getUserQQ());
        hrUser.setUserEmail(updateCurrentUserReq.getUserEmail());
        hrUser.setUserSex(updateCurrentUserReq.getUserSex());
        hrUser.setUserAddress(updateCurrentUserReq.getUserAddress());
        int a = hrUserMapper.updateByPrimaryKeySelective(hrUser);
        if (a>0){
            return ResultVO.result(ResultEnum.SUCCESS,a,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户信息更新失败！",false);
        }
    }

    @Override
    public Map<String, Object> deleteUserParticulars(DeleteUserParticularsReq deleteUserParticularsReq) {
        int a = hrUserMapper.deleteByPrimaryKey(deleteUserParticularsReq.getUserId());
        int b = 0;
        int c = 0;
        if (a>0){
            b = hrUserDepartmentMapper.deleteByUserId(deleteUserParticularsReq.getUserId());
            c = hrUserRoleMapper.deleteUserRole(deleteUserParticularsReq.getUserId());
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户信息删除失败！",false);
        }
        if (b>0 && c>0){
            return ResultVO.result(ResultEnum.SUCCESS,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户权限信息删除失败！",false);
        }
    }

    @Override
    public Map<String, Object> addUserParticulars(AddUserParticularsReq addUserParticularsReq) {
        HrUser hrUser = new HrUser();
        hrUser.setUserAccount(addUserParticularsReq.getUserAccount());
        hrUser.setUserPwd(addUserParticularsReq.getUserPwd());
        hrUser.setUserRealName(addUserParticularsReq.getUserRealName());
        hrUser.setUserPhone(addUserParticularsReq.getUserPhone());
        hrUser.setUserQQ(addUserParticularsReq.getUserQQ());
        hrUser.setUserEmail(addUserParticularsReq.getUserEmail());
        hrUser.setUserSex(addUserParticularsReq.getUserSex());
        hrUser.setUserAddress(addUserParticularsReq.getUserAddress());
        int a = hrUserMapper.insertSelective(hrUser);
        int b = 0;
        int c = 0;
        if (a>0){
            Integer userId = hrUser.getUserId();
            HrUserRole hrUserRole = new HrUserRole();
            hrUserRole.setUserId(userId);
            hrUserRole.setRoleId(addUserParticularsReq.getRoleId());
            b = hrUserRoleMapper.insertSelective(hrUserRole);

            HrUserDepartment hrUserDepartment = new HrUserDepartment();
            hrUserDepartment.setUserId(userId);
            hrUserDepartment.setDepartmentId(addUserParticularsReq.getDepartmentId());
            hrUserDepartment.setDutyId(addUserParticularsReq.getDutyId());
            c = hrUserDepartmentMapper.insertSelective(hrUserDepartment);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户信息新增失败！",false);
        }
        if (b>0 && c>0){
            return ResultVO.result(ResultEnum.SUCCESS,hrUser.getUserId(),true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,"用户权限信息新增失败！",false);
        }
    }

    @Override
    public List<UserOutline> getDepartmentUser(HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        String departmentId = hrUser.getDepartmentId();
        List<UserOutline> hrUsers = hrUserMapper.getUserByDepartmentId(departmentId);
        return hrUsers;
    }

}
