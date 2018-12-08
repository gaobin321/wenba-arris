package com.wenba.arris;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author qingjiang.li
 * @date 2018-11-16
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboBootStrap.class)
@WebAppConfiguration
public class ApplicationBaseTest {
    private static final Logger log = LoggerFactory.getLogger(ApplicationBaseTest.class);

    @Before
    public void init() {
        log.info("=====> 测试开始 <=====");
    }

    @After
    public void after() {
        log.info("=====> 测试结束 <=====");
    }
}

