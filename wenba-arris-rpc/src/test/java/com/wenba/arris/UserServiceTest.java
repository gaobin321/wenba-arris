package com.wenba.arris;

import com.wenba.arris.domain.User;
import com.wenba.arris.service.user.UserService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author qingjiang.li
 * @date 2018/11/29
 */
public class UserServiceTest extends ApplicationBaseTest {
    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    UserService userService;

    @Test
    public void test() throws Exception {
        User user = userService.queryUserById(100);
        log.info("user:{}", user);
    }

}
