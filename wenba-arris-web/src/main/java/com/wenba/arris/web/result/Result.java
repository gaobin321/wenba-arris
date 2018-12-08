package com.wenba.arris.web.result;

import java.io.Serializable;

/**
 * @author qingjiang.li
 * @date 2018-11-09
 */
public class Result<T> implements Serializable {
    private String msg;
    private int code;
    private T data;
    private Result(T data) {
        this.code = CodeMsg.SUCCESS.getCode();
        this.msg = CodeMsg.SUCCESS.getmsg();
        this.data = data;
    }
    private Result(CodeMsg cm) {
        if(cm == null){
            return;
        }
        this.code = cm.getCode();
        this.msg = cm.getmsg();
    }
    /**
     * 成功时候的调用
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    /**
     * 成功，不需要传入参数
     * @return
     */
    public static Result<String> success(){
        return success("");
    }
    /**
     * 失败时候的调用
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm){
        return new Result<T>(cm);
    }
    /**
     * 失败时候的调用,扩展消息参数
     * @param cm
     * @param msg
     * @return
     */
    public static <T> Result<T> error(CodeMsg cm, String msg){
        cm.setMsg(cm.getmsg()+"--"+msg);
        return new Result<T>(cm);
    }
    public T getData() {
        return data;
    }
    public String getMsg() {
        return msg;
    }
    public int getCode() {
        return code;
    }
}
