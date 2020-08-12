package com.system.recruit.entity;

import java.util.Date;

public class HrSysConfig {
    private String sysConfigName;

    private String sysConfigContent;

    private String sysConfigState;

    private Date createDate;

    private Date updateDate;

    public String getSysConfigName() {
        return sysConfigName;
    }

    public void setSysConfigName(String sysConfigName) {
        this.sysConfigName = sysConfigName == null ? null : sysConfigName.trim();
    }

    public String getSysConfigContent() {
        return sysConfigContent;
    }

    public void setSysConfigContent(String sysConfigContent) {
        this.sysConfigContent = sysConfigContent == null ? null : sysConfigContent.trim();
    }

    public String getSysConfigState() {
        return sysConfigState;
    }

    public void setSysConfigState(String sysConfigState) {
        this.sysConfigState = sysConfigState == null ? null : sysConfigState.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}