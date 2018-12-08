package com.wenba.arris.utils.base;

import java.io.Serializable;

/**
 * 正值 表示成功
 * 负值 表示失败
 * 正负值相应出现
 *
 * 拓展，继承此类，不同项目实现自定义
 */
public class BaseResultUtil implements Serializable {
    /* 基础 +|- [1,9] */

    // 成功
    public final static int SUCCESS_CODE = 1;
    public final static String SUCCESS_MSG = "success";

    public static BaseResult success() {
        return success(null);
    }

    public static BaseResult success(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(SUCCESS_CODE);
        baseResult.setMsg(SUCCESS_MSG);
        return baseResult;
    }

    // 失败
    public final static int FAIL_CODE = -1;
    public final static String FAIL_MSG = "fail";

    public static BaseResult fail() {
        return fail(null);
    }

    public static BaseResult fail(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(FAIL_CODE);
        baseResult.setMsg(FAIL_MSG);
        return baseResult;
    }

    // 参数异常
    public final static int PARAM_FAIL_CODE = -2;
    public final static String PARAM_FAIL_MSG = "参数异常";

    public static BaseResult paramFail(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(PARAM_FAIL_CODE);
        baseResult.setMsg(PARAM_FAIL_MSG);
        return baseResult;
    }

    // 分布式锁
    public final static int DISTRIBUTED_LOCKS_FAIL_CODE = -3;
    public final static String DISTRIBUTED_LOCKS_MSG = "分布式锁获取失败";

    public static BaseResult distributedLocksFail(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(DISTRIBUTED_LOCKS_FAIL_CODE);
        baseResult.setMsg(DISTRIBUTED_LOCKS_MSG);
        return baseResult;
    }

    // 参数为空
    public final static int PARAM_NULL_CODE = -4;
    public final static String PARAM_NULL_MSG = "参数为空";

    public static BaseResult paramNull(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(PARAM_NULL_CODE);
        baseResult.setMsg(PARAM_NULL_MSG);
        return baseResult;
    }

    // 未查询到结果
    public final static int RESULT_NULL_CODE = -5;
    public final static String RESULT_NULL_MSG = "未查询到结果";

    public static BaseResult resultNull(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(RESULT_NULL_CODE);
        baseResult.setMsg(RESULT_NULL_MSG);
        return baseResult;
    }

    // 该规则id或版本不存在
    public final static int DROOLS_IDORVERS_CODE = -6;
    public final static String DROOLS_IDORVERS_MSG = "该规则id或版本不存在";

    public static BaseResult droolsIdOrVers(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(DROOLS_IDORVERS_CODE);
        baseResult.setMsg(DROOLS_IDORVERS_MSG);
        return baseResult;
    }

    // 该规则id或版本或输出参数类型不存在
    public final static int DROOLS_IDORVERSOROP_CODE = -7;
    public final static String DROOLS_IDORVERSOROP_MSG = "该规则id或版本不存在";

    public static BaseResult droolsIdOrVersOrOp(BaseResult baseResult) {
        if (baseResult == null) {
            baseResult = new BaseResult();
        }
        baseResult.setCode(DROOLS_IDORVERSOROP_CODE);
        baseResult.setMsg(DROOLS_IDORVERSOROP_MSG);
        return baseResult;
    }


    /* 短信 +|- [10,19] */
    // 业务限流
    public final static int SMS_BUSINESS_LIMIT_CONTROL_CODE = -10;
    public final static String SMS_BUSINESS_LIMIT_CONTROL_MSG = "短信发送太频繁，请1分钟后再试！";
    // 超过当日上限
    public final static int SMS_DAY_LIMIT_CODE = -11;
    public final static String SMS_DAY_LIMIT_MSG = "当日发送的短信数量超出了上限！";

    // 余额不足
    public final static int SMS_AMOUNT_NOT_ENOUGH_CODE = -12;
    public final static String SMS_AMOUNT_NOT_ENOUGH_MSG = "账户余额不足，请联系管理员！";
    // 其它
    public final static int SMS_OTHER_CODE = -14;
    public final static String SMS_OTHER_MSG = "发送失败，请1分钟后再试！";

    // 短信验证码
    public final static int SMS_AUTHCODE_FAIL_CODE = -15;
    public final static String SMS_AUTHCODE_FAIL_MSG = "短信验证码错误";

    /* 用户 +|- [20,29] */
    // 用户存在
    public final static int USER_EXIST_FAIL_CODE = -20;
    public final static String USER_EXIST_FAIL_MSG = "用户已存在";
    // 用户不存在
    public final static int USER_NOT_EXIST_FAIL_CODE = -21;
    public final static String USER_NOT_EXIST_FAIL_MSG = "用户不存在";
    // 密码错误
    public static final int USER_PASSWORD_FAIL_CODE = -22;
    public static final String USER_PASSWORD_FAIL_MSG = "登录密码错误";
    // 在其它设备登录
    public static final int USER_LOGIN_IN_OHTER_FAIL_CODE = -23;
    public static final String USER_LOGIN_IN_OHTER_FAIL_MSG = "用户已在其它设备登录，请重新登录";

    public BaseResultUtil() {
    }

}
