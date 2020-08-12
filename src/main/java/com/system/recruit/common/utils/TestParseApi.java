package com.system.recruit.common.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.http.Consts;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.File;

public class TestParseApi {

    public static JSONObject testResumeParser(String url, String fname, int uid, String pwd) throws Exception {
    	// 设置头字段
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("content-type", "application/json");
        
        // 读取简历内容
    	byte[] bytes = FileUtils.readFileToByteArray(new File(fname));
    	String data = new String(Base64.encodeBase64(bytes), Consts.UTF_8);
    	
        // 设置内容信息
        JSONObject json = new JSONObject();
        json.put("uid", uid);			// 用户id
        json.put("pwd", pwd);			// 用户密码
        json.put("file_name", fname);	// 文件名
        json.put("file_cont", data);	// 经base64编码过的文件内容
        StringEntity params = new StringEntity(json.toString(), Consts.UTF_8);
        httpPost.setEntity(params);
        
        // 发送请求
        HttpClient httpclient = new DefaultHttpClient(); 
        HttpResponse response = httpclient.execute(httpPost);
        
        // 处理返回结果
        String resCont = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
        //System.out.println(resCont);
        
        JSONObject res = JSONObject.parseObject(resCont);
        /*JSONObject status = res.getJSONObject("status");
        if (status.getInteger("code") != 200) {
            System.out.println("request failed: code=<" + status.getInteger("code") + ">, message=<" + status.getString("message") + ">");
        } else {
            JSONObject acc = res.getJSONObject("account");
            System.out.println("usage_remaining:" + acc.getInteger("usage_remaining"));

            JSONObject result = res.getJSONObject("result");
            System.out.println("result:\n" + JSONObject.toJSONString(result));
            System.out.println("request succeeded");
        }*/
        return res;
    }

    public static JSONObject ToApi(String fname) throws Exception {
        String url = "http://www.resumesdk.com/api/parse";
        //String fname = "/Users/weikaimo/Desktop/text/51Job/51job_关小强_芯片分析工程师(309306578).doc";	//替换为你的文件名
        int uid = 2006190;		//替换为你的用户名（int格式）
        String pwd = "Iy2GxD";	//替换为你的密码（String格式
        JSONObject rest = testResumeParser(url, fname, uid, pwd);
        return rest;
    }
    
    public static void main(String[] args) throws Exception {
        String url = "http://www.resumesdk.com/api/parse";	
        String fname = "/Users/weikaimo/Desktop/text/51Job/51job_关小强_芯片分析工程师(309306578).doc";	//替换为你的文件名
        int uid = 2006190;		//替换为你的用户名（int格式）
        String pwd = "Iy2GxD";	//替换为你的密码（String格式）

        testResumeParser(url, fname, uid, pwd);
    }
}
