package com.wenba.arris.common.utils.http;

import com.wenba.arris.common.utils.json.FastJsonUtil;
import com.wenba.arris.common.utils.reflect.ReflectUtil;
import org.apache.http.impl.cookie.BasicClientCookie;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by imiiot on 2018/5/8.
 */
public class HttpParams {

    private static final String CHARACTER_SET = "utf-8";

    /**
     * 请求头
     */
    private final Map<String, String> headers = new HashMap<String, String>();

    /**
     * cookie
     */
    private final List<BasicClientCookie> cookies = new ArrayList<>();

    /**
     * x-www-form-urlencode 表单参数
     */
    private final Map<String, String> forms = new HashMap<String, String>();

    /**
     * application/json json参数
     */
    private String json;

    public HttpParams() {
    }

    /**
     * 获取请求头
     *
     * @return
     */
    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * 设置请求头参数信息
     * 返回this对象，方便连续多次调用
     *
     * @param key
     * @param value
     * @return
     */
    public HttpParams setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    /**
     * 获取cookie列表
     *
     * @return
     */
    public List<BasicClientCookie> getCookies() {
        return cookies;
    }

    /**
     * 设置cookie
     *
     * @param name
     * @param cookie
     * @return
     */
    public HttpParams setCookie(String name, String cookie) {
        return setCookie(name, cookie, null, null, null, null);
    }

    /**
     * 设置cookie
     *
     * @param name
     * @param cookie
     * @param domain
     * @param path
     * @param version
     * @param date
     * @return
     */
    public HttpParams setCookie(String name, String cookie, String domain, String path, Integer version, Date date) {
        BasicClientCookie basicClientCookie = new BasicClientCookie(name, cookie);

        if (domain == null || "".equals(domain)) {
            basicClientCookie.setDomain(".");
        } else {
            basicClientCookie.setDomain(domain);
        }

        if (path == null || "".equals(path)) {
            basicClientCookie.setPath("/");
        } else {
            basicClientCookie.setPath(path);
        }

        if (version == null || "".equals(version)) {
            basicClientCookie.setVersion(1);
        } else {
            basicClientCookie.setVersion(version);
        }

        if (date == null) { // 默认1天
            long expireMS = System.currentTimeMillis() + 24 * 60 * 60 * 1000L;
            basicClientCookie.setExpiryDate(new Date(expireMS));
        } else {
            basicClientCookie.setExpiryDate(date);
        }
        this.cookies.add(basicClientCookie);
        return this;
    }

    /**
     * 获取表单
     *
     * @return
     */
    public Map<String, String> getForms() {
        return forms;
    }

    /**
     * 设置表单参数信息
     * 返回this对象，方便连续多次调用
     *
     * @param key
     * @param value
     * @return
     */
    public HttpParams setForm(String key, String value) {
        this.forms.put(key, value);
        return this;
    }

    /**
     * 设置数据
     * form值为json，需要进行URLEncode编码
     *
     * @param key
     * @param json
     * @return
     */
    public HttpParams setFormJson(String key, String json) {
        try {
            this.forms.put(key, URLEncoder.encode(json, CHARACTER_SET));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * 设置Map
     * 遍历Map，表单参数名 为 key，表单参数值 value
     *
     * @param map
     * @return
     */
    public HttpParams setFormByMap(Map<String, String> map) {
        this.forms.putAll(map);
        return this;
    }

    /**
     * 设置对象
     * 反射，通过对象getter方法，获取对象属性，为form赋值
     *
     * @param obj
     * @return
     */
    public HttpParams setFormByObj(Object obj) {
        Map<String, Object> map = ReflectUtil.getFieldValueMapByGetter(obj);
        if (map != null) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                this.forms.put(entry.getKey(), entry.getValue().toString());
            }
        }
        return this;
    }

    /**
     * 设置对象
     * 提取有效参数。通过 & 分割获取参数对，通过 = 分割 获取参数名和值
     * 如 loginName=val1&password=val2，提取参数 loginName:p1,password:p2
     *
     * @param str
     * @return
     */
    public HttpParams setFormByUrlParams(String str) {
        if (str != null && !"".equals(str)) {
            String[] kvArr = str.split("&");
            for (String kv : kvArr) {
                // 参数值可能含有=，取第一个=
                int firstIndex = kv.indexOf("=");
                if (firstIndex > 0) {
                    setForm(kv.substring(0, firstIndex), kv.substring(firstIndex + 1));
                }
            }
        }
        return this;
    }


    /**
     * 获取json
     *
     * @return
     */
    public String getJson() {
        return json;
    }

    /**
     * 设置json参数
     *
     * @param json
     */
    public HttpParams setJson(String json) {
        this.json = json;
        return this;
    }

    /**
     * 设置对象
     *
     * @param obj
     * @return
     */
    public HttpParams setJsonByObj(Object obj) {
        this.json = FastJsonUtil.toJson(obj);
        return this;
    }
}
