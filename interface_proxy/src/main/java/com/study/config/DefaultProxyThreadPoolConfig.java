package com.study.config;

import com.study.util.SpringUtils;
import lombok.Getter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration(proxyBeanMethods = false)
public class DefaultProxyThreadPoolConfig implements InitializingBean, ProxyThreadPoolConfig {

    @Value("${proxy.thread.coreSize:0}")
    private int threadCoreSize;
    @Value("${proxy.thread.maxSize:0}")
    private int threadMaxSize;
    @Value("${proxy.thread.keepAliveTime:0}")
    private int keepAliveTime;
    @Value("${proxy.thread.queueSize:1000}")
    private int queueSize;

    @Getter
    private ThreadPoolExecutor threadPoolExecutor;

    @Override
    public void afterPropertiesSet() throws Exception {
        threadPoolExecutor = new ThreadPoolExecutor(threadCoreSize != 0 ? threadCoreSize : Runtime.getRuntime().availableProcessors(),
                threadMaxSize != 0 ? threadMaxSize : Runtime.getRuntime().availableProcessors() * 2
                , keepAliveTime, TimeUnit.MINUTES, new LinkedBlockingQueue<>(queueSize));
    }

    @ConditionalOnMissingBean
    @Bean
    public SpringUtils springUtils(){
        return new SpringUtils();
    }

}
