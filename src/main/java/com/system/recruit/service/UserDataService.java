package com.system.recruit.service;

import com.system.recruit.entity.UserOutline;
import com.system.recruit.entity.UserParticulars;
import com.system.recruit.entity.info.*;
import com.system.recruit.entity.info.GetAllUserReq;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface UserDataService {
    UserResp getCurrentUser(HttpServletRequest request);
    GetAllUserResp getAllUser(GetAllUserReq getAllUserReq);
    UserParticulars getUserParticulars(GetUserParticularsReq getUserParticularsReq);
    Map<String,Object> updateUserParticulars(UpdateUserParticularsReq updateUserParticularsReq);
    Map<String,Object> updateCurrentUser(UpdateCurrentUserReq updateCurrentUserReq);
    Map<String,Object> deleteUserParticulars(DeleteUserParticularsReq deleteUserParticularsReq);
    Map<String,Object> addUserParticulars(AddUserParticularsReq addUserParticularsReq);

    List<UserOutline> getDepartmentUser(HttpServletRequest request);

    List<UserResp> getUserByRoleId(GetUserByRoleIdReq getUserByRoleIdReq);

}
