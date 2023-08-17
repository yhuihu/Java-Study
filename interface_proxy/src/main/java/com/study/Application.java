package com.study;

import com.study.config.EnableProxyConfig;
import com.study.dao.DemoDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:51
 */
@SpringBootApplication
@EnableProxyConfig(basePackages = "com.study.dao")
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        DemoDao bean = run.getBean(DemoDao.class);
        bean.sayHello();
    }
}
