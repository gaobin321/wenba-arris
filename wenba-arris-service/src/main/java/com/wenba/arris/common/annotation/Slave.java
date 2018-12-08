package com.wenba.arris.common.annotation;

import java.lang.annotation.*;

/**
 * Created by liuguanqing on 16/5/10.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Slave {
    public String name() default "";
}
