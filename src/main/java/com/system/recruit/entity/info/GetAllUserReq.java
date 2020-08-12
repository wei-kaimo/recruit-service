package com.system.recruit.entity.info;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/13 0013 15:46
 */
public class GetAllUserReq {
    @NotBlank(message = "参数不能为空")
    private String pageSize;
    @NotBlank(message = "参数不能为空")
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
}
