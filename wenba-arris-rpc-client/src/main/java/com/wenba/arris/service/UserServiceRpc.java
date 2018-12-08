package com.wenba.arris.service;

import com.wenba.arris.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component("userServiceRpc")
@ImportResource(locations={"classpath:template-dubbo-client.xml"})
public class UserServiceRpc implements UserServiceFacade {

    private static final Logger log = LoggerFactory.getLogger(UserServiceRpc.class);
    @Autowired
    public UserServiceFacade userServiceFacade;

    @PostConstruct
    public void init() {
        log.info("test");
    }

    @Override
    public UserDto queryUserById(Integer userId) {
        return userServiceFacade.queryUserById(userId);
    }
}