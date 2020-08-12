package com.system.recruit.controller;

import com.alibaba.fastjson.JSONObject;
import com.system.recruit.common.Constant;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.tolead.ExcelData;
import com.system.recruit.common.utils.tolead.HttpUtil;
import com.system.recruit.common.utils.tolead.ToLeadPojo;
import com.system.recruit.dao.HrDepartmentMapper;
import com.system.recruit.entity.info.AddPositionReq;
import com.system.recruit.entity.info.InitiateRecruitReqReq;
import com.system.recruit.service.PositionDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/20 0020 14:43
 */
@RestController
@RequestMapping("/File")
@Slf4j
public class FileDataController {
    @Resource
    private PositionDataService positionDataService;
    @Resource
    private HrDepartmentMapper hrDepartmentMapper;

    @PostMapping("/upload")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName, HttpServletRequest request) {
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            /*String reg = ".+(.JPEG|.jpeg|.JPG|.jpg|.png|.PNG|.xls|.xlsx|.doc|.docx)$";
            Matcher matcher = Pattern.compile(reg).matcher(saveFileName);
            System.out.println(matcher.find());*/
            String fileType = saveFileName.substring(saveFileName.lastIndexOf("."));
            if (fileName != null && !"".equals(fileName)){
                fileName = fileName+fileType;
            }else {
                fileName = saveFileName;
            }
            log.info(fileName);
            File saveFile = new File(Constant.SYS_CONFIG.get("files_upload_path")+ fileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            String name = saveFile.getName();
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                return ResultVO.result(ResultEnum.SUCCESS, "上传成功", true);
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

    @PostMapping("/toLead")
    @PreAuthorize("isAuthenticated()")
    public Map<String, Object> toLead(@RequestParam("file") MultipartFile file) {
        /*ExcelData(String filePath,String sheetName){
            FileInputStream fileInputStream = null;
            try {
                fileInputStream = new FileInputStream(filePath);
                XSSFWorkbook sheets = new XSSFWorkbook(fileInputStream);
                //获取sheet
                sheet = sheets.getSheet(sheetName);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
        String filePath = Constant.SYS_CONFIG.get("files_upload_path");
        String host = "http://localhost:8080/";
        if ("dev".equals(Constant.ACTiVE)){
            filePath = "/Users/weikaimo/work/upload/";
            host = "http://127.0.0.1:9091/";
        }
        if (!file.isEmpty()) {
            String saveFileName = file.getOriginalFilename();
            File saveFile = new File(filePath+ saveFileName);
            if (!saveFile.getParentFile().exists()) {
                saveFile.getParentFile().mkdirs();
            }
            try {
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
                ExcelData sheet1 = new ExcelData(filePath+ saveFileName, "Sheet1");
                List<ToLeadPojo> list = sheet1.getCellToPojoList();
                for (ToLeadPojo toLeadPojo : list) {
                    String rest = HttpUtil.doPostFrom(host+"recruit/login","username="+toLeadPojo.getUserId()+"&password="+toLeadPojo.getPwd());
                    JSONObject restJSON = JSONObject.parseObject(rest);
                    if (203 == restJSON.getInteger("code")){
                        toLeadPojo.setJwtToken((String) restJSON.get("jwtToken"));
                        AddPositionReq addPositionReq = new AddPositionReq();
                        String departmentId = hrDepartmentMapper.selectByName(toLeadPojo.getDepartmentName());
                        if (departmentId == null || "".equals(departmentId)){
                            log.info("参数错误");
                        }
                        addPositionReq.setDepartmentId(departmentId);
                        addPositionReq.setCityName(toLeadPojo.getCityName());
                        addPositionReq.setJobNature(toLeadPojo.getJobNature());
                        addPositionReq.setPositionName(toLeadPojo.getPositionName());
                        addPositionReq.setPositionRequirements(toLeadPojo.getPositionRequirements());
                        addPositionReq.setPositionResponsibilities(toLeadPojo.getPositionResponsibilities());
                        Map<String,Object> addPositionRest = positionDataService.addPosition(addPositionReq);
                        if (101 == (Integer) addPositionRest.get("code")){
                            String positionId = (String) addPositionRest.get("data");
                            InitiateRecruitReqReq initiateRecruitReqReq = new InitiateRecruitReqReq();
                            initiateRecruitReqReq.setPositionId(positionId);
                            initiateRecruitReqReq.setPlanNum(toLeadPojo.getNum());
                            initiateRecruitReqReq.setInitiatorExamine("1");
                            initiateRecruitReqReq.setNeedNotApproval("1");
                            String initiateRecruitRest = HttpUtil.doPost(host+"recruit/RecruitReq/initiateRecruitReq",JSONObject.toJSONString(initiateRecruitReqReq),toLeadPojo.getJwtToken());
                            JSONObject initiateRecruitRestJSON = JSONObject.parseObject(initiateRecruitRest);
                            if (101 == initiateRecruitRestJSON.getInteger("code")){
                                log.info("成功");
                            }else {
                                log.info("失败");
                            }
                        }
                    }else {
                        System.out.println("帐号或密码错误，跳过");
                        continue;
                    }
                }
                return ResultVO.result(ResultEnum.SUCCESS, "上传成功", true);
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
}
