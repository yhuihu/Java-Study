package com.study.feign;

import com.study.feign.api.IFeignHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class ClientApplication implements CommandLineRunner {

    @Autowired
    IFeignHello feignHello;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class);
    }

    @Override
    public void run(String... args) throws Exception {
        feignHello.sayHi();
    }
}
