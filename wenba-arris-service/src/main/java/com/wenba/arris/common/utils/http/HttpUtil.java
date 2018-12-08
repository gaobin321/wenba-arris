package com.wenba.arris.common.utils.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * HTTP 工具类
 * 依赖 org.apache.httpcomponents 组件
 * 链接超时 或 链接被拒绝 应该提示用户 而非抛出异常
 * Created by imiiot on 2017/5/31.
 */
public final class HttpUtil {

    public static final String CHARSET = "UTF-8";

    // 失败重发间隔，毫秒
    private static int FREQUENCY_MS = 1000;

    private static final Logger log = LoggerFactory.getLogger(HttpUtil.class);

    // 私有构造函数防止实例化
    private HttpUtil() {
    }

    public static void setFrequencyMs(int frequencyMs) {
        FREQUENCY_MS = frequencyMs;
    }

    /**
     * 通用方法
     *
     * @param method
     * @param url
     * @param httpParams
     * @param times
     * @return
     */
    public static HttpResult<String> http(String method, String url, HttpParams httpParams, int times) {
        HttpResult<String> result = null;

        Map<String, String> headers = null;
        List<BasicClientCookie> cookies = null;
        Map<String, String> forms = null;
        String json = null;

        if (httpParams != null) {
            headers = httpParams.getHeaders();
            cookies = httpParams.getCookies();
            forms = httpParams.getForms();
            json = httpParams.getJson();
        }

        // 设置通用请求头
        if (headers == null) {
            headers = new HashMap<>();
        } else {
            // 通用请求头
        }

        // 设置通用cookie
        if (cookies == null) {
            cookies = new ArrayList<>();
        } else {
            // 通用cookie
        }

        if (forms != null && forms.size() > 0) {
            if (!headers.containsKey("Content-type") && !headers.containsKey("Content-Type")) {
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=" + CHARSET);
            }
        }

        if (json != null && !"".equals(json)) {
            if (!headers.containsKey("Content-type") && !headers.containsKey("Content-Type")) {
                headers.put("Content-Type", "application/json; charset=" + CHARSET);
            }
        }

        if (times <= 0) {
            times = 3;
        }

        for (int i = 0; i < times; i++) {

            if (HttpMethod.GET.equalsIgnoreCase(method)) {
                result = httpGet(url, headers, cookies, forms, null, i + 1);
            } else if (HttpMethod.POST.equalsIgnoreCase(method)) {
                result = httpPost(url, headers, cookies, forms, json, i + 1);
            } else if (HttpMethod.PUT.equalsIgnoreCase(method)) {
                result = httpPut(url, headers, cookies, forms, json, i + 1);
            } else if (HttpMethod.DELETE.equalsIgnoreCase(method)) {
                result = httpDelete(url, headers, cookies, forms, null, i + 1);
            } else {
                log.error("http method not support now");
            }

            if (isSuccess(result.getCode())) {
                break;
            } else {
                try {
                    Thread.sleep(FREQUENCY_MS);
                } catch (InterruptedException e) {
                    log.error(e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * get 请求
     *
     * @param url
     * @param headers
     * @param forms
     * @param times
     * @return
     */
    private static HttpResult<String> httpGet(String url, Map<String, String> headers, List<BasicClientCookie> cookies, Map<String, String> forms, String json, int times) {
        // realUrl
        String realUrl = realUrl(HttpMethod.GET, url, forms);

        // HttpGet
        HttpGet httpget = new HttpGet(realUrl);

        // headers
        setHeaders(httpget, headers);

        HttpResult<String> result = response(httpget, cookies);
        log.info("connect " + times + " times , method : " + HttpMethod.GET + " , url : " + realUrl + " , headers : " + headers + " , forms : " + forms + " , json : " + json + " , result : " + result);
        return result;
    }

    /**
     * post 请求
     *
     * @param url
     * @param headers
     * @param forms
     * @param times
     * @return
     */
    private static HttpResult<String> httpPost(String url, Map<String, String> headers, List<BasicClientCookie> cookies, Map<String, String> forms, String json, int times) {
        // realUrl
        String realUrl = realUrl(HttpMethod.POST, url, forms);

        // HttpPost
        HttpPost httpost = new HttpPost(realUrl);

        // headers
        setHeaders(httpost, headers);

        // 表单参数
        if (forms != null && forms.size() > 0) {
            // postPutFinalParams
            List<NameValuePair> list = postPutFinalParams(HttpMethod.POST, forms);
            if (list != null && list.size() > 0) {
                try {
                    httpost.setEntity(new UrlEncodedFormEntity(list, CHARSET));
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage());
                }
            }
        }

        // json参数
        if (json != null && !"".equals(json)) {
            httpost.setEntity(new StringEntity(json, CHARSET));
        }

        HttpResult<String> result = response(httpost, cookies);
        log.info("connect " + times + " times , method : " + HttpMethod.POST + " , url : " + realUrl + " , headers : " + headers + " , forms : " + forms + " , json : " + json + " , result : " + result);
        return result;
    }

    /**
     * put请求
     *
     * @param url
     * @param headers
     * @param forms
     * @param times
     * @return
     */
    private static HttpResult<String> httpPut(String url, Map<String, String> headers, List<BasicClientCookie> cookies, Map<String, String> forms, String json, int times) {
        // realUrl
        String realUrl = realUrl(HttpMethod.PUT, url, forms);

        // HttpPut
        HttpPut httpPut = new HttpPut(realUrl);

        // headers
        setHeaders(httpPut, headers);

        // 表单参数
        if (forms != null && forms.size() > 0) {
            // postPutFinalParams
            List<NameValuePair> list = postPutFinalParams(HttpMethod.PUT, forms);
            if (list != null && list.size() > 0) {
                try {
                    httpPut.setEntity(new UrlEncodedFormEntity(list, CHARSET));
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage());
                }
            }
        }

        // json参数
        if (json != null && !"".equals(json)) {
            httpPut.setEntity(new StringEntity(json, CHARSET));
        }

        HttpResult<String> result = response(httpPut, cookies);
        log.info("connect " + times + " times , method : " + HttpMethod.PUT + " , url : " + realUrl + " , headers : " + headers + " , forms : " + forms + " , json : " + json + " , result : " + result);
        return result;
    }

    /**
     * delete 请求
     *
     * @param url
     * @param headers
     * @param forms
     * @param times
     * @return
     */
    private static HttpResult<String> httpDelete(String url, Map<String, String> headers, List<BasicClientCookie> cookies, Map<String, String> forms, String json, int times) {
        // realUrl
        String realUrl = realUrl(HttpMethod.DELETE, url, forms);

        // HttpDelete
        HttpDelete httpDelete = new HttpDelete(realUrl);

        // headers
        setHeaders(httpDelete, headers);

        HttpResult<String> result = response(httpDelete, cookies);
        log.info("connect " + times + " times , method : " + HttpMethod.DELETE + " , url : " + realUrl + " , headers : " + headers + " , forms : " + forms + " , json : " + json + " , result : " + result);
        return result;
    }

    /**
     * 实际请求路径
     * GET 或 DELETE 方法，将参数拼接至URL
     * POST 或 PUT 方法 ，URL无需修改
     *
     * @param method
     * @param url
     * @param forms
     * @return
     */
    private static String realUrl(final String method, final String url, final Map<String, String> forms) {
        StringBuilder realUrl = new StringBuilder();
        realUrl.append(url);

        // HttpUtil.GET 或 HttpUtil.DELETE 方法
        if (HttpMethod.GET.equalsIgnoreCase(method) || HttpMethod.DELETE.equalsIgnoreCase(method)) {
            if (url.contains("?")) {
                // 最后一个字母不为?
                if (url.lastIndexOf("?") < url.length() - 1) {
                    realUrl.append("&");
                }
            } else {
                realUrl.append("?");
            }
            if (forms != null && forms.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = forms.entrySet().iterator();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    realUrl.append(entry.getKey() + "=" + entry.getValue());
                    realUrl.append("&");
                }
            }
            realUrl = realUrl.deleteCharAt(realUrl.length() - 1);
        }


        return realUrl.toString();
    }

    /**
     * 参数转化
     * POST 或 PUT 方法 将表单参数转化为 List<NameValuePair>
     *
     * @param method
     * @param forms
     * @return
     */
    private static List<NameValuePair> postPutFinalParams(final String method, final Map<String, String> forms) {
        if (HttpMethod.POST.equals(method) || HttpMethod.PUT.equals(method)) {
            if (forms != null && forms.size() > 0) {
                Iterator<Map.Entry<String, String>> iterator = forms.entrySet().iterator();
                List<NameValuePair> list = new ArrayList<>();
                while (iterator.hasNext()) {
                    Map.Entry<String, String> entry = iterator.next();
                    list.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                return list;
            }
        }
        return null;
    }

    /**
     * 设置请求头
     *
     * @param request
     * @param headers
     */
    private static void setHeaders(final HttpUriRequest request, final Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            Iterator<Map.Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                request.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 执行请求，获取响应
     *
     * @param request
     * @return
     */
    private static HttpResult<String> response(HttpUriRequest request, List<BasicClientCookie> cookies) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpResult<String> result = new HttpResult<>();
        try {

            CookieStore cookieStore = new BasicCookieStore();

            // 设置cookie
            for (BasicClientCookie basicClientCookie : cookies) {
                cookieStore.addCookie(basicClientCookie);
            }

            // HttpClient
            // httpClient = HttpClients.createDefault();
            httpClient = HttpClients.custom()
                    .setDefaultCookieStore(cookieStore)
                    .build();

            // 执行请求
            response = httpClient.execute(request);

            // 获取响应状态
            StatusLine statusLine = response.getStatusLine();
            result.setCode(statusLine.getStatusCode());
            result.setMessage(statusLine.getReasonPhrase());

            // 获取返回实体
            HttpEntity entity = response.getEntity();
            // 获取响应数据，指定编码格式
            result.setData(EntityUtils.toString(entity, CHARSET));
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            } finally {
                response = null;
                httpClient = null;
            }
        }
        return result;
    }

    /**
     * 根据 http 状态码，确定是否成功执行
     * 100~200 继续发送请求
     * 200~300 成功
     * 300~400 重定向
     * 400~500 未发现资源
     * 500~ 服务器内部错误
     *
     * @param code
     * @return
     */
    private static boolean isSuccess(int code) {
        if (code >= 200 && code < 300) {
            return true;
        }
        return false;
    }
}
