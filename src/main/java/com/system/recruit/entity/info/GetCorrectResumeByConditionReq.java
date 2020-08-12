package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 18:25
 */
public class GetCorrectResumeByConditionReq {
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageSize;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String pageNum;

    private String resumeId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String requestId;

    private String sort;

    private String taster;

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

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getTaster() {
        return taster;
    }

    public void setTaster(String taster) {
        this.taster = taster;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
