package com.study.comsumer.queue.handle;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 自定义注解，用以决策关注的消息
 * @date 2022/7/31 14:48
 */
@Target({TYPE})
@Retention(RUNTIME)
public @interface HandleInfo {
    String[] subscribe() default {};

}
