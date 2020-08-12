package com.system.recruit.entity.info;

import com.system.recruit.entity.GetResumeContent;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/5 17:29
 */
public class GetResumeResp {
    private List<GetResumeContent> getResumeContents;
    private String count;

    public List<GetResumeContent> getGetResumeContents() {
        return getResumeContents;
    }

    public void setGetResumeContents(List<GetResumeContent> getResumeContents) {
        this.getResumeContents = getResumeContents;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
