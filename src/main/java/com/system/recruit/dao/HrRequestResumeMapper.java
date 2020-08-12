package com.system.recruit.dao;

import com.github.pagehelper.Page;
import com.system.recruit.entity.HrRequestResume;
import com.system.recruit.entity.RestCorrectResumeByCondition;
import com.system.recruit.entity.info.GetCorrectResumeByConditionReq;

public interface HrRequestResumeMapper {
    int deleteByPrimaryKey(String resumeId);

    int getCountById(String resumeId);

    int insert(HrRequestResume record);

    int insertSelective(HrRequestResume record);

    HrRequestResume selectByPrimaryKey(String resumeId);

    Page<RestCorrectResumeByCondition> getCorrectResumeByCondition(GetCorrectResumeByConditionReq getCorrectResumeByConditionReq);

    int updateByPrimaryKeySelective(HrRequestResume record);

    int updateByPrimaryKey(HrRequestResume record);
}