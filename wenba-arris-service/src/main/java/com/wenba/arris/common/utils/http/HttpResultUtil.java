package com.wenba.arris.common.utils.http;

/**
 * Created by imiiot on 2017/9/1.
 */
public final class HttpResultUtil<T> {
    private HttpResultUtil() {
    }

    public HttpResult<T> newInstance() {
        return new HttpResult<T>();
    }

    public HttpResult<T> newInstance(T data) {
        HttpResult<T> result = new HttpResult<T>();
        result.setData(data);
        return result;
    }

    public void success(HttpResult<T> result) {
        result.setCode(1);
        result.setMessage("success");
    }

    public void fail(HttpResult<T> result) {
        result.setCode(-1);
        result.setMessage("fail");
    }
}
