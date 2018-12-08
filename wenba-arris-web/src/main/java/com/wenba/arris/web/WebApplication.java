package com.wenba.arris.web;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(exclude = {
        DruidDataSourceAutoConfigure.class, DataSourceAutoConfiguration.class
}, scanBasePackages = { "com.wenba.arris" })
@MapperScan(basePackages = { "mapper", "com.wenba.*.dao.**" })
//@ImportResource(locations={"classpath:template-dubbo-client.xml"})
public class WebApplication {
    private static final Logger log = LoggerFactory.getLogger(WebApplication.class);

    public static void main(final String[] args) {
        SpringApplication.run(WebApplication.class, args);
        log.info("Example service start successful.");
    }

}

