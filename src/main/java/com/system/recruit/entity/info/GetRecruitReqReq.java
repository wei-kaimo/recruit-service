package com.system.recruit.entity.info;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/16 0016 16:45
 */
public class GetRecruitReqReq {
    @NotBlank(message = "参数不能为空")
    private String type;//1为用人部视角  2为人事部门
    @NotBlank(message = "参数不能为空")
    private String pageSize;
    @NotBlank(message = "参数不能为空")
    private String pageNum;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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
}
