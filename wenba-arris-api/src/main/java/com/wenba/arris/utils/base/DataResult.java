package com.wenba.arris.utils.base;

public class DataResult<T> extends BaseResult {
    // 请求结果数据
    T data;

    public DataResult() {
        super();
    }

    public DataResult(T data) {
        super();
        this.data = data;
    }

    public DataResult(int code, String msg, T data) {
        super(code, msg);
        this.data = data;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
