package com.study.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/8/17 20:50
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(ProxyClassRegistrar.class)
public @interface EnableProxyConfig {

    String[] basePackages() default {};

}
