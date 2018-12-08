package com.wenba.arris.base;

import java.io.Serializable;

/**
 * Created by imiiot on 2017/5/25.
 */
public class Result<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     */
    public void success() {
        this.setCode(1);
        this.setMessage("success");
    }

    /**
     * 失败
     */
    public void fail() {
        this.setCode(-1);
        this.setMessage("失败");
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
