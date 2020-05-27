package com.study.demo;

import com.study.demo.source.MySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo
 **/
@SpringBootApplication
@EnableBinding(MySource.class)
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
