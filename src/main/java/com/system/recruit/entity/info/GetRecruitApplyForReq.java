package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/28 12:41
 */
public class GetRecruitApplyForReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;
    private String stateId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageSize;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageNum;

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

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
