package com.system.recruit.entity.info;

import com.system.recruit.entity.HrResume;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 18:52
 */
public class GetResumeByConditionResp {
    private List<HrResume> hrResumeList;
    private String count;

    public List<HrResume> getHrResumeList() {
        return hrResumeList;
    }

    public void setHrResumeList(List<HrResume> hrResumeList) {
        this.hrResumeList = hrResumeList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
