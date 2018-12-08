package com.wenba.arris.common.utils;

import com.wenba.arris.base.Result;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

/**
 * Created by imiiot on 2017/5/31.
 * XXX 实现原生态的功能，只是作为平台来传递信息，不做正确性验证
 */
public class UtilHttp {
    private static Logger log = LoggerFactory.getLogger(UtilHttp.class);
    // 间隔频率
    private static final int FREQUENCY = 1000;

    /**
     * 传递参数通过 NameValuePair , 其实现类 为 BaseNameValuePair
     *
     * @param method
     * @param url
     * @param nvps
     * @param reties
     * @return
     */
    public static Result<String> http(String method, String url, List<NameValuePair> nvps, int reties) throws IOException {
        Result<String> result = new Result<String>();
        if (reties <= 0) {
            reties = 3;
        }

        for (int i = 0; i < reties; i++) {
            int times = i + 1;
            if ("GET".equals(method.toUpperCase())) {
                result = httpGet(url, nvps, times);
            } else if ("POST".equals(method.toUpperCase())) {
                result = httpPost(url, nvps, times);
            } else {
                log.info("method not support now");
            }

            log.info("result " + UtilFastJson.toJson(result));

            if (result.getCode() == 200) {
                break;
            } else {
                try {
                    Thread.sleep(FREQUENCY);
                } catch (InterruptedException e) {
                    log.info(e.getMessage());
                }
                continue;
            }
        }
        return result;
    }

    private static Result<String> httpGet(String url, List<NameValuePair> nvps, int times) throws IOException {
        Result<String> result = new Result<String>();
        result.fail();

        // HttpClient
        CloseableHttpClient client = HttpClients.createDefault();

        StringBuilder realUrl = new StringBuilder();
        realUrl.append(url);
        if (url.contains("?")) {
            realUrl.append("&");
        } else {
            realUrl.append("?");
        }
        if (nvps != null && nvps.size() > 0) {
            for (NameValuePair nameValuePair : nvps) {
                realUrl.append(nameValuePair.getName() + "=" + nameValuePair.getValue());
                realUrl.append("&");
            }
        }
        realUrl = realUrl.deleteCharAt(realUrl.length() - 1);
        if (times > 1) {
            log.info("try get " + times + " times," + realUrl);
        }
        // HttpGet
        HttpGet httpget = new HttpGet(realUrl.toString());
        httpget.setHeader("charset", "utf-8");
        httpget.setHeader("signValue", "bafb8a82f46aff1245b48928d042f012e8645941");

        // 执行get请求
        CloseableHttpResponse response = client.execute(httpget);

        // 获取响应状态
        StatusLine statusLine = response.getStatusLine();
        result.setCode(statusLine.getStatusCode());
        result.setMessage(statusLine.getReasonPhrase());

        // 获取返回实体
        HttpEntity entity = response.getEntity();
        // 获取响应数据，指定编码格式
        result.setData(EntityUtils.toString(entity, "utf-8"));

        // 关闭
        client.close();
        response.close();

        return result;
    }

    private static Result<String> httpPost(String url, List<NameValuePair> nvps, int times) throws IOException {
        Result<String> result = new Result<String>();
        result.fail();

        // HttpClient
        CloseableHttpClient httpclient = HttpClients.createDefault();
        if (times > 1) {
            log.info("try post " + times + "times," + url);
        }

        // HttpPost
        HttpPost httpost = new HttpPost(url);
        httpost.setHeader("charset", "utf-8");
        httpost.setHeader("signValue", "bafb8a82f46aff1245b48928d042f012e8645941");

        if (nvps != null && nvps.size() > 0) {
            httpost.setEntity(new UrlEncodedFormEntity(nvps));
        }
        CloseableHttpResponse response = httpclient.execute(httpost);

        // 获取响应状态
        StatusLine statusLine = response.getStatusLine();
        result.setCode(statusLine.getStatusCode());
        result.setMessage(statusLine.getReasonPhrase());

        // 获取返回实体
        HttpEntity entity = response.getEntity();
        // 获取响应数据，指定编码格式
        result.setData(EntityUtils.toString(entity, "utf-8"));

        // 关闭
        httpclient.close();
        response.close();
        return result;
    }
}
