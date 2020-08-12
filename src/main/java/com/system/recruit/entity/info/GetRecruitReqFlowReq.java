package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 19:37
 */
public class GetRecruitReqFlowReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageSize;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageNum;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String state;

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
