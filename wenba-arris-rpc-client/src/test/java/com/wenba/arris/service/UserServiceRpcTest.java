package com.wenba.arris.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wenba.arris.dto.UserDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@DubboComponentScan(basePackages = "com.wenba.arris.service")
@SpringBootApplication(scanBasePackages = { "com.wenba.arris.service" })
public class UserServiceRpcTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceRpcTest.class);
    @Autowired
    UserServiceRpc userServiceRpc;

    @Reference(application = "${application.name}")
    public UserServiceFacade userServiceFacade;

    @Before
    public void main() {
        ConfigurableApplicationContext run = new SpringApplicationBuilder(UserServiceRpcTest.class)
                .web(WebApplicationType.NONE)
                .run(new String[0]);
        UserServiceFacade bean = run.getBean(UserServiceFacade.class);
        UserDto userDto = bean.queryUserById(123);
    }

    @Test
    public void queryUserById() {
        while (userServiceRpc.userServiceFacade == null) {
            log.info("wait for dubbo");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                log.warn("", e);
            }
        }
        UserDto userDto = userServiceRpc.queryUserById(1);
    }
}