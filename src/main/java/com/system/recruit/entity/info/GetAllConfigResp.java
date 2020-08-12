package com.system.recruit.entity.info;

import com.system.recruit.entity.HrConfig;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 16:52
 */
public class GetAllConfigResp {
    private List<HrConfig> hrConfigList;
    private String count;

    public List<HrConfig> getHrConfigList() {
        return hrConfigList;
    }

    public void setHrConfigList(List<HrConfig> hrConfigList) {
        this.hrConfigList = hrConfigList;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
