package com.wenba.arris.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @ Author
 * @ Date       ：Created in 5:43 PM 2018/10/24
 * @ Version    ：1.0
 * @ Modified By：
 * @ Description：基类，仅数据
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataResult<T> extends BaseResult {
    T data;

    public static <T> DataResult<T> ofError(int code, String errMsg) {
        DataResult<T> r = new DataResult<T>();
        r.setCode(code);
        r.setMsg(errMsg);
        r.setData(null);
        return r;
    }

    public static <T> DataResult<T> of(T data) {
        DataResult<T> r = new DataResult<T>();
        r.setCode(200);
        r.setMsg("成功");
        r.setData(data);
        return r;
    }
}
