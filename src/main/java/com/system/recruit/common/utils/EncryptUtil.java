package com.system.recruit.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: Weikaimo
 * @Date: 2019/8/26 0026 15:11
 */
public class EncryptUtil {
    public static void main(String[] args) {//主程序入口
        MD5("s1");
    }

    public static String MD5(String sourceStr) {
        //通过result返回结果值
        String result = "";
        try {
            //1.初始化MessageDigest信息摘要对象,并指定为MD5不分大小写都可以
            MessageDigest md = MessageDigest.getInstance("MD5");
            //2.传入需要计算的字符串更新摘要信息，传入的为字节数组byte[],将字符串转换为字节数组使用getBytes()方法完成
            md.update(sourceStr.getBytes());
            //3.计算信息摘要digest()方法,返回值为字节数组
            byte b[] = md.digest();

            int i;//定义整型
            //声明StringBuffer对象
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                //将首个元素赋值给i
                i = b[offset];
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    //前面补0
                    buf.append("0");
                }
                //转换成16进制编码
                buf.append(Integer.toHexString(i));
            }
            //转换成字符串
            result = buf.toString();
            //输出32位16进制字符串
            System.out.println("MD5(" + sourceStr + ",32) = " + result);
            //输出16位16进制字符串
            System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        //返回结果
        return result;
    }
}
