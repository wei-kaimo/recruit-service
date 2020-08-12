package com.system.recruit.entity.info;

import com.system.recruit.entity.HrApplyFor;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/31 20:57
 */
public class GetRecruitApplyForResp {
    private List<HrApplyFor> hrApplyForList;
    private String count;

    public List<HrApplyFor> getHrApplyForList() {
        return hrApplyForList;
    }

    public void setHrApplyForList(List<HrApplyFor> hrApplyForList) {
        this.hrApplyForList = hrApplyForList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
