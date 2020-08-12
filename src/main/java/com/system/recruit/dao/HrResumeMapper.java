package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.GetResumeContent;
import com.system.recruit.entity.HrEducationalBackground;
import com.system.recruit.entity.HrResume;
import com.system.recruit.entity.HrWorkExperience;
import com.system.recruit.entity.info.AddResumeReq;
import com.system.recruit.entity.info.GetResumeByConditionReq;

import java.util.List;

public interface HrResumeMapper {
    int deleteByPrimaryKey(String resumeId);

    int insert(HrResume record);

    int insertSelective(HrResume record);

    int insertByAddResumeReq(AddResumeReq addResumeReq);

    HrResume selectByPrimaryKey(String resumeId);

    List<HrWorkExperience> getWorkExperienceList (String resumeId);
    List<HrEducationalBackground> getEducationalBackgroundList (String resumeId);

    Page<HrResume> selectByCondition(GetResumeByConditionReq getResumeByConditionReq);

    int selectByKeywords(AddResumeReq record);

    Page<GetResumeContent> selectAllByCondition(HrResume hrResume);

    int updateByPrimaryKeySelective(HrResume record);

    int updateByPrimaryKey(HrResume record);
}