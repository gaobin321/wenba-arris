package com.wenba.arris.rpc.service;

import com.wenba.arris.domain.User;
import com.wenba.arris.dto.UserDto;
import com.wenba.arris.service.UserServiceFacade;
import com.wenba.arris.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public class UserServiceProvider implements UserServiceFacade {
    private static final Logger log = LoggerFactory.getLogger(UserServiceProvider.class);

    @PostConstruct
    private void init() {
        log.info("init");
    }

    @Autowired
    UserService userService;

    @Override
    public UserDto queryUserById(Integer userId) {
        log.info("start");
        User user = userService.queryUserById(userId);
        log.info("end");
        UserDto userDto = new UserDto();
        userDto.setId(user.getId().longValue());
        userDto.setName(user.getUsername());
        return userDto;
    }
}
