package com.system.recruit.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.system.recruit.common.Constant;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.common.utils.TestParseApi;
import com.system.recruit.dao.*;
import com.system.recruit.entity.*;
import com.system.recruit.entity.info.*;
import com.system.recruit.service.ResumeDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/30 17:16
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ResumeDataServiceImpl implements ResumeDataService {

    @Resource
    private HrResumeMapper hrResumeMapper;
    @Resource
    private HrRequestResumeMapper hrRequestResumeMapper;
    @Resource
    private GetUserDateUtil getUserDateUtil;
    @Resource
    private HrEducationalBackgroundMapper hrEducationalBackgroundMapper;
    @Resource
    private HrWorkExperienceMapper hrWorkExperienceMapper;
    @Resource
    private HrSysConfigMapper hrSysConfigMapper;

    @Override
    public GetResumeByConditionResp getResumeByCondition(GetResumeByConditionReq getResumeByConditionReq, HttpServletRequest request) {
        PageHelper.startPage(Integer.parseInt(getResumeByConditionReq.getPageNum()), Integer.parseInt(getResumeByConditionReq.getPageSize()));
        Page<HrResume> hrResumes = hrResumeMapper.selectByCondition(getResumeByConditionReq);
        List<HrResume> restList = hrResumes.getResult();
        String count = String.valueOf(hrResumes.getTotal());
        GetResumeByConditionResp getResumeByConditionResp = new GetResumeByConditionResp();
        getResumeByConditionResp.setCount(count);
        getResumeByConditionResp.setHrResumeList(restList);
        return getResumeByConditionResp;
    }

    @Override
    public Map<String, Object> correctResumeByPosition(CorrectResumeReq correctResumeReq, HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        hrRequestResumeMapper.deleteByPrimaryKey(correctResumeReq.getResumeId());
        HrRequestResume hrRequestResume = new HrRequestResume();
        hrRequestResume.setResumeId(correctResumeReq.getResumeId());
        hrRequestResume.setRequestId(correctResumeReq.getRequestId());
        hrRequestResume.setRemark(correctResumeReq.getRemark());
        hrRequestResume.setSort(Integer.valueOf(correctResumeReq.getSort()));
        hrRequestResume.setState(correctResumeReq.getState());
        hrRequestResume.setTaster(String.valueOf(hrUser.getUserId()));
        int a = hrRequestResumeMapper.insertSelective(hrRequestResume);
        if (a<=0){
            return ResultVO.failure(ResultEnum.FAILURE,a,false);
        }
        return ResultVO.result(ResultEnum.SUCCESS,a,true);
    }

    @Override
    public GetCorrectResumeByConditionResp getCorrectResumeByCondition(GetCorrectResumeByConditionReq getCorrectResumeByConditionReq, HttpServletRequest request) {
        PageHelper.startPage(Integer.parseInt(getCorrectResumeByConditionReq.getPageNum()), Integer.parseInt(getCorrectResumeByConditionReq.getPageSize()));
        Page<RestCorrectResumeByCondition> restCorrectResumeByConditions = hrRequestResumeMapper.getCorrectResumeByCondition(getCorrectResumeByConditionReq);
        GetCorrectResumeByConditionResp getCorrectResumeByConditionResp = new GetCorrectResumeByConditionResp();
        getCorrectResumeByConditionResp.setRestCorrectResumeByConditions(restCorrectResumeByConditions.getResult());
        getCorrectResumeByConditionResp.setCount(String.valueOf(restCorrectResumeByConditions.getTotal()));
        return getCorrectResumeByConditionResp;
    }

    @Override
    public Map<String, Object> uploadResume(MultipartFile file, HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        if (!file.isEmpty()) {
            String fileName = "";
            SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
            String time = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
            String saveFileName = file.getOriginalFilename();
            fileName = hrUser.getUsername()+"_"+time+"_"+saveFileName;
            String filePath = Constant.SYS_CONFIG.get("files_upload_path") + fileName;
            log.info("文件名："+fileName);
            log.info("路径："+filePath);
            File saveFile = new File(filePath);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                Object string = fileName;
                return ResultVO.result(ResultEnum.SUCCESS,string,true);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                return ResultVO.failure(ResultEnum.FAILURE.getCode(), e.getMessage());
            }
        } else {
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), "文件为空");
        }
    }

    @Override
    public Map<String, Object> getUploadContent(GetUploadContentReq getUploadContentReq, HttpServletRequest request) {
        try {
            GetUploadContentResp getUploadContentResp = new GetUploadContentResp();
            //TODO 解析文档
            /*FileBean fileBean = FileBeanParser.getFileBean(new File(Constant.SYS_CONFIG.get("files_upload_path")+getUploadContentReq.getFilePath()));
            String content = fileBean.getContent();
            log.info(fileBean.getContent());
            getUploadContentResp.setCandidatesName(getContinuousStr(getDeployList("resume_name_sign"),content));
            getUploadContentResp.setAge(getContinuousStr(getDeployList("resume_age_sign"),content));
            getUploadContentResp.setEducation(getContinuousStr(getDeployList("resume_education_sign"),content));
            getUploadContentResp.seteMail(getContinuousStr(getDeployList("resume_eMail_sign"),content));
            getUploadContentResp.setNativePlace(getContinuousStr(getDeployList("resume_nativePlace_sign"),content));
            getUploadContentResp.setPhoneNumber(getContinuousStr(getDeployList("resume_phoneNumber_sign"),content));
            getUploadContentResp.setSex(getContinuousStr(getDeployList("resume_sex_sign"),content));
            getUploadContentResp.setSource(getContinuousStr(getDeployList("resume_source_sign"),content));
            getUploadContentResp.setYearsOfWorking(getContinuousStr(getDeployList("resume_yearsOfWorking_sign"),content));*/
            //getUploadContentResp
            String uploadPath = Constant.SYS_CONFIG.get("files_upload_path");
            if ("dev".equals(Constant.ACTiVE)){
                uploadPath = "/Users/weikaimo/work/upload/";
            }
            JSONObject restJson = TestParseApi.ToApi(uploadPath+getUploadContentReq.getFilePath());
            JSONObject status = restJson.getJSONObject("status");
            JSONObject result = new JSONObject();
            if(status.getInteger("code") != 200) {
                //System.out.println("request failed: code=<" + status.getInteger("code") + ">, message=<" + status.getString("message") + ">");
                log.info("api解析接口失败："+status.toJSONString());
                return ResultVO.failure(ResultEnum.FAILURE.getCode(), "文件解析异常，请手动添加");
            }
            else {
                JSONObject acc = restJson.getJSONObject("account");
                //System.out.println("usage_remaining:" + acc.getInteger("usage_remaining"));
                log.info("api接口调用量信息："+acc.toJSONString());
                result = restJson.getJSONObject("result");
                //System.out.println("result:\n" + JSONObject.toJSONString(result));
                //System.out.println("request succeeded");

            }

            getUploadContentResp.setCandidatesName(result.getString("name"));
            getUploadContentResp.setNativePlace(result.getString("hukou_address_norm"));
            getUploadContentResp.setAge(result.getString("age"));
            getUploadContentResp.setSex(result.getString("gender"));
            getUploadContentResp.setPhoneNumber(result.getString("phone"));
            getUploadContentResp.seteMail(result.getString("email"));
            getUploadContentResp.setYearsOfWorking(result.getString("work_year"));
            getUploadContentResp.setEducation(result.getString("degree"));
            getUploadContentResp.setSource(result.getString("resume_source"));
            String raw_text = result.getString("raw_text");
            String[] raw_texts = (raw_text.split("\n"));
            getUploadContentResp.setRawText(raw_texts);
            return ResultVO.result(ResultEnum.SUCCESS, getUploadContentResp, true);
        } catch (Exception e) {
            //e.printStackTrace();
            log.error(e.getMessage());
            return ResultVO.failure(ResultEnum.FAILURE.getCode(), "文件解析异常，请手动添加");
        }
    }
    private List<String>  getDeployList(String key){
        String sysConfigs = hrSysConfigMapper.selectByPrimaryKey(key);
        List<String> getDeployList = Arrays.asList(sysConfigs.split("\\$"));
        return getDeployList;
    }

    private String getContinuousStr(List<String> deployList,  String Str){
        for (String s : deployList) {
            int index = Str.indexOf(s);
            if (index != -1) {
                String output = Str.substring(index + s.length(), index + s.length() + 100);
                output = output.replace(":","");
                output = output.replace("：","");
                char[] outputChar = output.toCharArray();
                output = "";
                int n = 0;
                for (int i = 0; i < outputChar.length; i++) {
                    if (outputChar[i] != ' ' && outputChar[i] != '\n' && outputChar[i] != '\t' && outputChar[i] != '\r') {
                        output = output+outputChar[i];
                        n++;
                    }else if (n>0){
                        break;
                    }
                }
                return output;
            }
        }
        return "";
    }
    private List<String> getPhone (String testString){
        char[] chars = testString.toCharArray();
        ArrayList<String> charsList = new ArrayList<>();//所有11位字符
        for(int i = 0; i < chars.length; i++){
            StringBuilder stringBuilder = new StringBuilder();
            for(int j = 0; j < 11; j++){
                if(i + j < chars.length){
                    stringBuilder.append(chars[i + j]);
                }
            }
            if(stringBuilder.length()==11){
                charsList.add(stringBuilder.toString());
            }
        }
        List<String> phoneList = new ArrayList<>();
        String str = "^0\\d{2,3}-?\\d{7,8}$|^(13[0-9]|15[0-9]|18[0-9]|14[0-9]|17[0-9])\\d{8}$";
        for (String s : charsList) {
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(s);
            if (m.find()){
                System.out.println(s);
                phoneList.add(s);
            }
        }
        return phoneList;
    }
    private String testEmail(String Email){
        String str = "[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(Email);
        if (m.find()){
            System.out.println(Email);
            return Email;
        }else{
            return "";
        }
    }

    @Override
    public Map<String, Object> addResume(AddResumeReq resumeReq, HttpServletRequest request) {
        String resumeId = UUID.randomUUID().toString();
        resumeReq.setResumeId(resumeId);
        String filePath = resumeReq.getResumeFile();
        filePath = Constant.SYS_CONFIG.get("files_upload_serve_location")+filePath;
        resumeReq.setResumeFile(filePath);
        int a = hrResumeMapper.selectByKeywords(resumeReq);
        if (a>0){
           return ResultVO.failure(ResultEnum.FAILURE.getCode(),"该简历已存在");
        }
        hrResumeMapper.insertByAddResumeReq(resumeReq);
        List<HrEducationalBackground> hrEducationalBackgrounds = resumeReq.getHrEducationalBackgrounds();
        if (hrEducationalBackgrounds!=null && hrEducationalBackgrounds.size()>0){
            for (int i = 0; i < hrEducationalBackgrounds.size(); i++) {

                hrEducationalBackgrounds.get(i).setId(i);
                hrEducationalBackgrounds.get(i).setResumeId(resumeId);
                if (hrEducationalBackgrounds.get(i).getStartTime() == null){
                    hrEducationalBackgrounds.get(i).setStartTime("");
                }
                if (hrEducationalBackgrounds.get(i).getEndTime() == null){
                    hrEducationalBackgrounds.get(i).setEndTime("");
                }
                if (hrEducationalBackgrounds.get(i).getGoing() == null){
                    hrEducationalBackgrounds.get(i).setGoing("");
                }
                if (hrEducationalBackgrounds.get(i).getSchoolName() == null){
                    hrEducationalBackgrounds.get(i).setSchoolName("");
                }
                if (hrEducationalBackgrounds.get(i).getEducation() == null){
                    hrEducationalBackgrounds.get(i).setEducation("");
                }
                if (hrEducationalBackgrounds.get(i).getMajor() == null){
                    hrEducationalBackgrounds.get(i).setMajor("");
                }
            }
            hrEducationalBackgroundMapper.insertList(hrEducationalBackgrounds);
        }


        List<HrWorkExperience> hrWorkExperienceList = resumeReq.getHrWorkExperienceList();
        if (hrWorkExperienceList!=null && hrWorkExperienceList.size()>0){
            for (int i = 0; i < hrWorkExperienceList.size(); i++) {
                hrWorkExperienceList.get(i).setId(i);
                hrWorkExperienceList.get(i).setResumeId(resumeId);
                if (hrWorkExperienceList.get(i).getStartTime() == null){
                    hrWorkExperienceList.get(i).setStartTime("");
                }
                if (hrWorkExperienceList.get(i).getCompanyName() == null){
                    hrWorkExperienceList.get(i).setCompanyName("");
                }
                if (hrWorkExperienceList.get(i).getPositionName() == null){
                    hrWorkExperienceList.get(i).setPositionName("");
                }
                if (hrWorkExperienceList.get(i).getJobDescription() == null){
                    hrWorkExperienceList.get(i).setJobDescription("");
                }
                if (hrWorkExperienceList.get(i).getProjectExperience() == null){
                    hrWorkExperienceList.get(i).setProjectExperience("");
                }
                if (hrWorkExperienceList.get(i).getProjectNum() == null){
                    hrWorkExperienceList.get(i).setProjectNum("");
                }
            }
            hrWorkExperienceMapper.insertList(hrWorkExperienceList);
        }

        return ResultVO.result(ResultEnum.SUCCESS, true);
    }

    @Override
    public GetResumeResp getResume(GetResumeReq getResumeReq, HttpServletRequest request) {
        HrResume hrResume = new HrResume();
        hrResume.setResumeId(getResumeReq.getResumeId());
        hrResume.setPositionId(getResumeReq.getPositionId());
        hrResume.setResumeFile(getResumeReq.getResumeFile());
        hrResume.setCandidatesName(getResumeReq.getCandidatesName());
        hrResume.setNativePlace(getResumeReq.getNativePlace());
        hrResume.setAge(getResumeReq.getAge());
        hrResume.setSex(getResumeReq.getSex());
        hrResume.setPhoneNumber(getResumeReq.getPhoneNumber());
        hrResume.seteMail(getResumeReq.geteMail());
        hrResume.setYearsOfWorking(getResumeReq.getYearsOfWorking());
        hrResume.setEducation(getResumeReq.getEducation());
        hrResume.setSource(getResumeReq.getSource());
        PageHelper.startPage(Integer.parseInt(getResumeReq.getPageNum()), Integer.parseInt(getResumeReq.getPageSize()));
        Page<GetResumeContent> getResumeContents = hrResumeMapper.selectAllByCondition(hrResume);
        GetResumeResp getResumeResp = new GetResumeResp();
        getResumeResp.setCount(String.valueOf(getResumeContents.getTotal()));
        getResumeResp.setGetResumeContents(getResumeContents.getResult());
        return getResumeResp;
    }
}
