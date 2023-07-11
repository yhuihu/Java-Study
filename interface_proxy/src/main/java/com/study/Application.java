package com.study;

import com.study.config.ProxyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:51
 */
@SpringBootApplication
@EnableConfigurationProperties(ProxyProperties.class)
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);

    }
}
