package com.study.config;

import com.study.util.Constant;

import java.lang.annotation.*;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/8/17 20:50
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ProxyComponent {

    String impl() default Constant.undefinedString;

}
