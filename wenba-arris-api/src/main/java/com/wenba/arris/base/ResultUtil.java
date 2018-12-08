package com.wenba.arris.base;

import java.io.Serializable;

/**
 * Created by imiiot on 2017/6/1.
 */
public class ResultUtil implements Serializable {
    public static Result success() {
        Result result = new Result();
        result.setCode(1);
        result.setMessage("success");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(-1);
        result.setMessage("fail");
        return result;
    }

}
