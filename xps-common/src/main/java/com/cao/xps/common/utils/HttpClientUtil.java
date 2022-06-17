package com.cao.xps.common.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * http请求工具类
 */
public class HttpClientUtil {

    private static final Logger logger= LoggerFactory.getLogger(HttpClientUtil.class);

    private static HttpClient httpClient;

    private static HttpClient getIntanceHttpClient() {
        if (httpClient == null) {
            httpClient = HttpClients.createDefault();
        }
        return httpClient;
    }

    /**
     * http中请求路径
     * @param url 请求路径（需要以 http:// 开头）
     * @return
     */
    public static String getHttpClientGet(String url) {
        String responseContent = null;
        HttpGet get = new HttpGet(url);
        get.addHeader("Content-type", "application/json;charset=utf-8");
        get.setHeader("Accept", "application/json");
        StringEntity paramEntity = null;
        try {
            HttpResponse response = null;
            response = getIntanceHttpClient().execute(get);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
            System.out.println(statusCode);
//			if (statusCode == 200) {
            responseContent = EntityUtils.toString(entity);
//			}
            System.out.println("请求接口：" + url + ",返回: \n" + responseContent);
        } catch (Exception e) {
            logger.error("参数赋值异常" + e.getMessage(), e);
        }
        return responseContent;
    }

    /**
     * http中post请求
     * @param url 请求路径
     * @param parastr 参数（可为json格式）
     * @return
     */
    public static String getHttpClientPost(String url, String parastr) {
        logger.info("http请求：url="+url+",parastr="+parastr);
        String responseContent = null;
        HttpPost post = new HttpPost(url);
        post.addHeader("Content-type", "application/json;charset=utf-8");
        post.setHeader("Accept", "application/json");

        StringEntity paramEntity = null;
        try {

            paramEntity = new StringEntity(parastr, "utf-8");
            post.setEntity(paramEntity);

            HttpResponse response = null;
            response = getIntanceHttpClient().execute(post);
            HttpEntity entity = response.getEntity();
            int statusCode = response.getStatusLine().getStatusCode();
//			if (statusCode == 200) {
            responseContent = EntityUtils.toString(entity);
//			}
            logger.info("http请求返回状态："+statusCode+"请求接口：" + url + ",参数：" + parastr + ",返回: \n" + responseContent);
        } catch (IOException e) {
            logger.error("参数赋值异常" + e.getMessage(), e);
        }
        return responseContent;
    }




}
