package com.study.comsumer.queue.handle;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/7/31 14:48
 */
public interface Handle<T> {

    static final Logger logger = LoggerFactory.getLogger(Handle.class);

    void handle(T msg);

    default Integer getOrder() {
        return Integer.MIN_VALUE;
    }

}
