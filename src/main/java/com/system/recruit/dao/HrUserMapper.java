package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.UserOutline;
import com.system.recruit.entity.UserParticulars;
import com.system.recruit.entity.info.UserResp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HrUserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(HrUser record);

    int insertSelective(HrUser record);

    HrUser selectByPrimaryKey(Integer userId);

    HrUser getUserByUserAccount(String userAccount);

    List<UserResp> gerUserListByRoleId(@Param("roleIds") List<String> roleIds);

    List<HrUser> getAllUser();

    List<UserOutline> getUserByDepartmentId(String departmentId);

    Page<UserOutline> getUserOutline();

    UserParticulars getUserParticulars(Integer userId);

    int updateByPrimaryKeySelective(HrUser record);

    int updateByPrimaryKey(HrUser record);
}
