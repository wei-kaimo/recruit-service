package com.system.recruit.entity;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author weikaimo
 * @version 1.0
 * @date 2020/5/26 16:15
 */
public class GetMailContent {
    private VelocityContext velocityContext;
    private String templateName;


    public VelocityContext getVelocityContext() {
        return velocityContext;
    }

    public void setVelocityContext(VelocityContext velocityContext) {
        this.velocityContext = velocityContext;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getMailContent() throws IOException {
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
