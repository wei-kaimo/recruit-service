package com.system.recruit.entity.info;

import com.system.recruit.entity.HrRole;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/12 0012 21:35
 */
public class GetAllRoleResp {
    private List<HrRole> hrRoleList ;
    private String count;

    @Override
    public String toString() {
        return "GetAllRoleResp{" +
                "hrRoleList=" + hrRoleList +
                ", count='" + count + '\'' +
                '}';
    }

    public List<HrRole> getHrRoleList() {
        return hrRoleList;
    }

    public void setHrRoleList(List<HrRole> hrRoleList) {
        this.hrRoleList = hrRoleList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
