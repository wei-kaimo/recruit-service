package com.system.recruit.entity.info;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/17 0017 20:45
 */
public class GetPositionListResp {
    List<HrPositionResp> hrPositions;
    private String count;

    public List<HrPositionResp> getHrPositions() {
        return hrPositions;
    }

    public void setHrPositions(List<HrPositionResp> hrPositions) {
        this.hrPositions = hrPositions;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
