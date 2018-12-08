package com.wenba.arris.web.result;

/**
 * @author qingjiang.li
 * @date 2018-11-09
 */
public enum CodeMsg {
    // 按照模块定义CodeMsg
    // HTTP 状态码
    // 2xx (成功)表示成功处理了请求的状态代码
    SUCCESS(200, "成功"),
    // 4xx(请求错误) 这些状态代码表示请求可能出错，妨碍了服务器的处理

    // 500xxx (服务器错误)这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错
    // 5001xx 通用异常
    SERVER_EXCEPTION(500100, "服务端异常"),
    PARAMETER_ISNULL(500101, "输入参数为空"),
    // 5002xx 业务异常
    USER_NOT_EXSIST(500200, "用户不存在"),
    NOT_FIND_DATA(500201, "查找不到对应数据"),
    ADD_FAIL(500202, "添加失败");

    private int code;
    private String msg;

    private CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getmsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
