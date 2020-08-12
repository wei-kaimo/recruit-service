package com.system.recruit.common.utils;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.ByteArrayOutputStream;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/26 0026 19:37
 */
public class XmlUtil {
    public static String createXml(Object obj , Class...  classes) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(classes);
        // 获取上下文对象
        Marshaller marshaller = context.createMarshaller(); // 根据上下文获取marshaller对象

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  // 设置编码字符集
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true); // 格式化XML输出，有分行和缩进
        //marshaller.marshal(getSimpleDepartment(),System.out);   // 打印到控制台
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        marshaller.marshal(obj, baos);
        String xmlObj = new String(baos.toByteArray());         // 生成XML字符串
        return xmlObj;
    }
}
