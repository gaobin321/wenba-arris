package com.wenba.arris.base;

import java.io.Serializable;

/**
 * @ Author
 * @ Date       ：Created in 6:32 PM 2018/10/24
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：基类工具类
 */
public class BaseResultUtil implements Serializable {
    public static final int SUCCESS_CODE = 200;
    public static final String SUCCESS_MSG = "success";

    public static final int FAIL_CODE = -1;
    public static final String FAIL_MSG = "fail";

    public static final int PARAMETER_FAIL_CODE = -2;
    public static final String PARAMETER_FAIL_MSG = "参数异常";

    public static final int ACCESS_FAIL_CODE = -3;
    public static final String ACCESS_FAIL_MSG = "没有访问权限";


    public static BaseResult success() {
        return success((BaseResult) null);
    }

    public static BaseResult success(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }

        baseResult.setCode(SUCCESS_CODE);
        baseResult.setMsg(SUCCESS_MSG);
        return baseResult;
    }

    public static BaseResult fail() {
        return fail((BaseResult) null);
    }

    public static BaseResult fail(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }

        baseResult.setCode(FAIL_CODE);
        baseResult.setMsg(FAIL_MSG);
        return baseResult;
    }

    public BaseResultUtil() {
    }
}
