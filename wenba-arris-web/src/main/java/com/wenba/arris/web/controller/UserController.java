package com.wenba.arris.web.controller;

import com.wenba.arris.dto.UserDto;
import com.wenba.arris.service.UserServiceRpc;
import com.wenba.arris.service.user.UserService;
import com.wenba.arris.web.result.CodeMsg;
import com.wenba.arris.web.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wenba/template")
@ComponentScan(basePackages = "com.wenba.arris.service")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceRpc userServiceRpc;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Result queryUser(@PathVariable(name = "id", required = true) Integer id) {
        UserDto user = null;
        try {
            user = userServiceRpc.queryUserById(id);
            return Result.success(user);
        } catch (Exception e) {
            log.error("get user exception, uid={}", id, e);
        }
        return Result.error(CodeMsg.SERVER_EXCEPTION);
    }
}
