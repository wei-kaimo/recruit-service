package com.system.recruit.entity.info;

import com.system.recruit.entity.RestCorrectResumeByCondition;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 19:02
 */
public class GetCorrectResumeByConditionResp {
    private List<RestCorrectResumeByCondition> restCorrectResumeByConditions;
    private String count;

    public List<RestCorrectResumeByCondition> getRestCorrectResumeByConditions() {
        return restCorrectResumeByConditions;
    }

    public void setRestCorrectResumeByConditions(List<RestCorrectResumeByCondition> restCorrectResumeByConditions) {
        this.restCorrectResumeByConditions = restCorrectResumeByConditions;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
