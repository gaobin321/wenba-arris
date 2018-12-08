package com.wenba.arris.web.controller;

import com.wenba.arris.web.result.CodeMsg;
import com.wenba.arris.web.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BaseController {
    private static final Logger log = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public Object hello(String name) {
        try {
            log.info("hello {}", name);
            //todo add hello logical
            return Result.success(name);
        } catch (IllegalArgumentException e) {
            log.warn(e.getMessage(), e);
            return Result.error(CodeMsg.SERVER_EXCEPTION, e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return Result.error(CodeMsg.SERVER_EXCEPTION, "系统异常,请重试");
        }
    }
}
