package com.study.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:49
 */
@ConfigurationProperties(prefix = "proxy")
@Configuration
@Data
public class ProxyProperties {

    /**
     * 扫描配置，允许存在多个扫描路径
     */
    private ScanProperties[] scanProperties;

    /**
     * 配置刷新周期
     */
    private String refreshCron;
}

