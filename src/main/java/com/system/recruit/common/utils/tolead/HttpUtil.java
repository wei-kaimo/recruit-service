package com.system.recruit.common.utils.tolead;

import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by lsy
 */
public class HttpUtil {
    public static final String DEFAULT_CHARSET = "utf-8";

    public static String doGet(String baseUrl, Map<String, String> paramMap) {
        StringBuffer queryString = new StringBuffer();
        for (String param : paramMap.keySet()) {
            String value = paramMap.get(param);
            if (StringUtils.isNotBlank(value)) {
                try {
                    value = URLEncoder.encode(value, DEFAULT_CHARSET);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                queryString.append(param).append("=").append(value).append("&");
            }
        }
        return doGet(baseUrl, queryString.toString());
    }

    /**
     * 向指定URL发送GET方法的请求
     *
     * @param baseUrl 发送请求的URL
     * @param param   请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String doGet(String baseUrl, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString =baseUrl;
            if(param!=null){
                urlNameString = baseUrl + "?" + param;
            }
            System.out.println("http请求：" + urlNameString);
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "text/plain; charset=utf-8");
            connection.setRequestProperty("Charsert", DEFAULT_CHARSET);
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                // 获取所有响应头字段
                Map<String, List<String>> map = connection.getHeaderFields();
                // 遍历所有的响应头字段
                for (String key : map.keySet()) {
                    System.out.println(key + "--->" + map.get(key));
                }
                // 定义 BufferedReader输入流来读取URL的响应
                in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            }


        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String doGet(String baseUrl) {
        return doGet(baseUrl,"");
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String doPost(String url, String param, String bearer) {
        bearer = "Bearer "+bearer;
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Authorization",bearer);
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String doPostFrom(String url, String param ) {
        String plusEncode = null;
        try {
            plusEncode = URLEncoder.encode("+", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        param = param.replaceAll("\\+", plusEncode);
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "PostmanRuntime/7.25.0");
            // 发送POST请求必须设置如下两行
            connection.setDoOutput(true);
            connection.setDoInput(true);

            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String a = doPost("http://localhost:9091/recruit/Position/addPosition",
                "{\n" +
                        "    \"positionName\": \"测试测试测his\",\n" +
                        "    \"departmentId\": \"10201\",\n" +
                        "    \"cityName\": \"杭州\",\n" +
                        "    \"jobNature\": \"全日制\",\n" +
                        "    \"positionRequirements\": \"<p>  \\n\\t\\t\\t请问2</p><p><br></p><p>dqwqwd</p><p>wdqw</p>\",\n" +
                        "    \"positionResponsibilities\": \"<p>  \\n\\t\\t\\t wdwqf12f</p><p>dqwv</p>\"\n" +
                        "}",
                "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImlwIjoiMTI3LjAuMC4xIiwiZXhwIjoxNTkzNzc2NjM1fQ.AORaqtPr6xclrEQLIPIdiXNYKGlS4ZBNpdivPVljAboqBm_Rqaac8ghNPPRlkRUQe-3RxRU98znGjveqx2tK8rKFwu7MR52ApnwNhoWBRndVNXOb87NwfUCQufjv-0z30Vxlgl2NoZ-bokNb3j3WX3W3oABsZbri7Rcv08ja6cA");
        System.out.println(a);
    }
}
