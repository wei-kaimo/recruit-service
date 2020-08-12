package com.system.recruit.service.impl;

import com.sun.mail.util.MailSSLSocketFactory;
import com.system.recruit.common.Enums.ResultEnum;
import com.system.recruit.common.VO.ResultVO;
import com.system.recruit.common.utils.GetUserDateUtil;
import com.system.recruit.dao.HrPositionMapper;
import com.system.recruit.dao.HrResumeMapper;
import com.system.recruit.entity.Email;
import com.system.recruit.entity.GetMailContent;
import com.system.recruit.entity.HrResume;
import com.system.recruit.entity.HrUser;
import com.system.recruit.entity.info.SendInterviewEmailReq;
import com.system.recruit.entity.info.SendReexamineEmailReq;
import com.system.recruit.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.StringWriter;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;


@Service
@Slf4j
@Transactional(rollbackFor=Exception.class)
public class MailServiceImpl implements MailService {
    @Resource
    private HrResumeMapper hrResumeMapper;
    @Resource
    private HrPositionMapper hrPositionMapper;
    @Resource
    private GetUserDateUtil getUserDateUtil;

    @Override
    public Map<String, Object> sendInterviewEmail(SendInterviewEmailReq sendInterviewEmailReq, HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        HrResume hrResume = hrResumeMapper.selectByPrimaryKey(sendInterviewEmailReq.getResumeId());
        GetMailContent getMailContent = new GetMailContent();
        getMailContent.setTemplateName("interviewTemplate.vm");
        VelocityContext velocityContext = new VelocityContext();
        String sex = "";
        if ("1".equals(hrResume.getSex())){
            sex = "先生";
        }else {
            sex = "女士";
        }
        velocityContext.put("name",hrResume.getCandidatesName()+sex);
        velocityContext.put("companyName","杭州旗捷科技有限公司");
        velocityContext.put("position",hrPositionMapper.selectByPrimaryKey(hrResume.getPositionId()).getPositionName());
        velocityContext.put("interviewTime",sendInterviewEmailReq.getInterviewTime());
        velocityContext.put("salary","薪资面议");
        velocityContext.put("phone",hrUser.getUserPhone());
        velocityContext.put("address","杭州市江干区xxxx");
        velocityContext.put("busRoutes","122路公交转23路公交xxx路口下车向西200米xxx大楼");
        velocityContext.put("subwayRoutes","地铁2号线钱江路站转5号线xx站b口出向西200米");
        Date date = new Date();
        String str = "yyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        velocityContext.put("currentTime",sdf.format(date));
        velocityContext.put("tel","62331222");
        getMailContent.setVelocityContext(velocityContext);
        boolean flag = sendEmail(hrResume.geteMail(),"面试邀请函",getMailContent);
        if (flag){
            return ResultVO.failure(ResultEnum.SUCCESS,true,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,false,false);
        }
    }

    @Override
    public Map<String, Object> sendReexamineEmail(SendReexamineEmailReq sendReexamineEmailReq, HttpServletRequest request) {
        HrUser hrUser = getUserDateUtil.getUserDateInternalUtil(request);
        HrResume hrResume = hrResumeMapper.selectByPrimaryKey(sendReexamineEmailReq.getResumeId());
        GetMailContent getMailContent = new GetMailContent();
        getMailContent.setTemplateName("reexamineTemplate.vm");
        VelocityContext velocityContext = new VelocityContext();
        String sex = "";
        if ("1".equals(hrResume.getSex())){
            sex = "先生";
        }else {
            sex = "女士";
        }
        velocityContext.put("name",hrResume.getCandidatesName()+sex);
        velocityContext.put("stageName",sendReexamineEmailReq.getStageName());
        velocityContext.put("positionName",hrPositionMapper.selectByPrimaryKey(hrResume.getPositionId()).getPositionName());
        velocityContext.put("interviewTime",sendReexamineEmailReq.getInterviewTime());
        velocityContext.put("nextStage",sendReexamineEmailReq.getNextStageName());
        velocityContext.put("phone",hrUser.getUserPhone());
        velocityContext.put("address","杭州市江干区xxxx");
        velocityContext.put("busRoutes","122路公交转23路公交xxx路口下车向西200米xxx大楼");
        velocityContext.put("subwayRoutes","地铁2号线钱江路站转5号线xx站b口出向西200米");
        Date date = new Date();
        String str = "yyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        velocityContext.put("currentTime",sdf.format(date));
        velocityContext.put("tel","62331222");
        getMailContent.setVelocityContext(velocityContext);
        boolean flag = sendEmail(hrResume.geteMail(),"复试邀请函",getMailContent);
        if (flag){
            return ResultVO.failure(ResultEnum.SUCCESS,true,true);
        }else {
            return ResultVO.failure(ResultEnum.FAILURE,false,false);
        }
    }

    /*public boolean sendEmail(String email, String subject, GetMailContent content, boolean html){
        try {
            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setFrom("1004711425@qq.com");//设置发信人，发信人需要和spring.mail.username配置的一样否则报错
            message.setTo("wkm1004711425@163.com");				//设置收信人
            message.setSubject(subject);	//设置主题
            message.setText(content.getMailContent(),html);  	//第二个参数true表示使用HTML语言来编写邮件
            this.mailSender.send(mimeMessage);
            return true;
        } catch (Exception ex) {
            log.error(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
    }*/

    private synchronized JavaMailSenderImpl getJavaMailSender(Email email) throws GeneralSecurityException {
        // 获取邮箱发送实例


        String host = email.getHost();
        String username = email.getUsername();
        String password = email.getPassword();
        String sslPort = email.getSslPort();
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.setHost(host);
        javaMailSenderImpl.setUsername(username);
        javaMailSenderImpl.setPassword(password);
        javaMailSenderImpl.setDefaultEncoding("UTF-8");
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", sslPort);
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        javaMailSenderImpl.setJavaMailProperties(properties);
        return javaMailSenderImpl;
    }

    @Override
    public boolean sendEmail(String to, String subject, GetMailContent content) {
        Email email = new Email();
        email.setHost("smtp.163.com");
        email.setUsername("wkm1004711425@163.com");
        email.setPassword("JAYQNDDQWGNZPUKT");
        email.setSslPort("465");
        to = "wkm1004711425@icloud.com";
        log.info("简单邮件开始发送");
        try {
            JavaMailSenderImpl javaMailSender = getJavaMailSender(email);
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            String username = javaMailSender.getUsername();
            log.info("当前发送邮箱: " + username);
            messageHelper.setFrom(username,"韦凯谟");
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content.getMailContent(),true);
            javaMailSender.send(message);
            log.info("HTML邮件发送成功");
            return true;
        } catch (Exception e) {
            log.error("简单邮件发送异常", e);
            e.printStackTrace();
        }
        return false;
    }

    public String getMailContent(VelocityContext velocityContext,String templateName) throws IOException {
        StringWriter stringWriter = new StringWriter();
        // velocity引擎
        VelocityEngine velocityEngine = new VelocityEngine();
        // 设置文件路径属性
        Properties properties = new Properties();
        String dir = this.getClass().getResource("/").getPath();
        properties.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, dir + "templates/");
        // 引擎初始化属性配置
        velocityEngine.init(properties);
        // 加载指定模版
        Template template = velocityEngine.getTemplate(templateName, "utf-8");
        // 写到模板
        template.merge(velocityContext, stringWriter);
        return stringWriter.toString();
    }
}
