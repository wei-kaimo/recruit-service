package com.system.recruit.common.utils.tolead;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/26 18:27
 */
public class ToLeadPojo {
    private String departmentName;
    private String positionName;
    private String cityName;
    private String positionRequirements;
    private String jobNature;
    private String num;
    private String positionResponsibilities;
    private String pwd;
    private String userId;
    private String jwtToken;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getPositionName() {
        return positionName;
    }

    public void setPositionName(String positionName) {
        this.positionName = positionName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPositionRequirements() {
        return positionRequirements;
    }

    public void setPositionRequirements(String positionRequirements) {
        this.positionRequirements = positionRequirements;
    }

    public String getJobNature() {
        return jobNature;
    }

    public void setJobNature(String jobNature) {
        this.jobNature = jobNature;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getPositionResponsibilities() {
        return positionResponsibilities;
    }

    public void setPositionResponsibilities(String positionResponsibilities) {
        this.positionResponsibilities = positionResponsibilities;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
