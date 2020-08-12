package com.system.recruit.entity.info;

import com.system.recruit.common.config.ErrorMsg;
import com.system.recruit.entity.HrEducationalBackground;
import com.system.recruit.entity.HrWorkExperience;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/6/5 17:03
 */
public class AddResumeReq {
    private String resumeId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String positionId;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String resumeFile;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String candidatesName;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String nativePlace;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String age;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String sex;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String phoneNumber;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String eMail;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String yearsOfWorking;

    private String education;
    @NotBlank(message = ErrorMsg.SOS00000001)
    private String source;

    private List<HrWorkExperience> hrWorkExperienceList;

    private List<HrEducationalBackground> hrEducationalBackgrounds;

    public List<HrWorkExperience> getHrWorkExperienceList() {
        return hrWorkExperienceList;
    }

    public void setHrWorkExperienceList(List<HrWorkExperience> hrWorkExperienceList) {
        this.hrWorkExperienceList = hrWorkExperienceList;
    }

    public List<HrEducationalBackground> getHrEducationalBackgrounds() {
        return hrEducationalBackgrounds;
    }

    public void setHrEducationalBackgrounds(List<HrEducationalBackground> hrEducationalBackgrounds) {
        this.hrEducationalBackgrounds = hrEducationalBackgrounds;
    }

    public String getPositionId() {
        return positionId;
    }

    public void setPositionId(String positionId) {
        this.positionId = positionId;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    public String getCandidatesName() {
        return candidatesName;
    }

    public void setCandidatesName(String candidatesName) {
        this.candidatesName = candidatesName;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getYearsOfWorking() {
        return yearsOfWorking;
    }

    public void setYearsOfWorking(String yearsOfWorking) {
        this.yearsOfWorking = yearsOfWorking;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getResumeId() {
        return resumeId;
    }

    public void setResumeId(String resumeId) {
        this.resumeId = resumeId;
    }
}
