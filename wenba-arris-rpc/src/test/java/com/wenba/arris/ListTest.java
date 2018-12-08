package com.wenba.arris;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @author qingjiang.li
 * @date 2018/11/29
 */
public class ListTest {
    private static final Logger log = LoggerFactory.getLogger(ListTest.class);

    @Test
    public void test() {
        List<String> list = Lists.newArrayList("aaa", "bbb", "ccc", "ddd", "eee");
        List<String> sublist = list.subList(1, 2);
        log.info("sublist:{}", sublist);
    }

}
