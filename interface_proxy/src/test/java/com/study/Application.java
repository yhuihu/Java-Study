package com.study;

import com.study.config.EnableProxyConfig;
import com.study.demo.dao.DemoDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:51
 */
@SpringBootTest(classes = Application.ProxyConfiguration.class)
@DirtiesContext
public class Application {

    @Autowired
    DemoDao demoDao;

    @Test
    public void daoTest() {
        demoDao.sayHello();
    }

    @EnableProxyConfig(basePackages = {"com.study.demo"})
    @SpringBootConfiguration
    @EnableAutoConfiguration
    @ComponentScan(basePackages = "com.study.demo")
    protected static class ProxyConfiguration {

    }
}
