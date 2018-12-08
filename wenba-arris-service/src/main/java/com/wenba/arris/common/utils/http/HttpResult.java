package com.wenba.arris.common.utils.http;

import java.io.Serializable;

/**
 * Created by imiiot on 2017/5/25.
 */
public class HttpResult<T> implements Serializable {
    private Integer code = 404;
    private String message = "Not Found";
    private T data;

    public HttpResult() {
    }

    public HttpResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public HttpResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "code : " + code + " , message : " + message + " , data : " + data;
    }
}
