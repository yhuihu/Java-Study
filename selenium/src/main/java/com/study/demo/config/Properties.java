package com.study.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yhhu
 * @date 2020/11/24
 * @description
 */
@Configuration
@ConfigurationProperties(prefix = "selenium")
public class Properties {
    private String driverPath;

    public String getDriverPath() {
        return driverPath;
    }

    public void setDriverPath(String driverPath) {
        this.driverPath = driverPath;
    }
}
