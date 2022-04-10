package com.study.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        System.setProperty("jgroups.connection.url","jdbc:mysql://localhost:3306/jpa");
        System.setProperty("jgroups.connection.username","root");
        System.setProperty("jgroups.connection.password","123456");
        System.setProperty("jgroups.connection.driver","com.mysql.cj.jdbc.Driver");
        SpringApplication.run(Application.class, args);
    }
}
