package com.wenba.arris.utils.base;

import java.io.Serializable;

/**
 * 通用返回结构
 */
public class BaseResult implements Serializable {
    //请求结果编码
    int code;

    //请求结果文字说明
    String msg;

    // 服务器响应时间戳 ???
    // Long timestamp;

    public BaseResult() {
    }

    public BaseResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
