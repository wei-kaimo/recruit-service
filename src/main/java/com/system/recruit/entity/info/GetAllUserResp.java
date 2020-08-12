package com.system.recruit.entity.info;

import com.system.recruit.entity.UserOutline;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/13 0013 15:48
 */
public class GetAllUserResp {
    private List<UserOutline> hrUserList;
    private String count;

    @Override
    public String toString() {
        return "GetAllUserResp{" +
                "hrUserList=" + hrUserList +
                ", count='" + count + '\'' +
                '}';
    }

    public List<UserOutline> getHrUserList() {
        return hrUserList;
    }

    public void setHrUserList(List<UserOutline> hrUserList) {
        this.hrUserList = hrUserList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
