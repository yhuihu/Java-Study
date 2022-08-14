package com.study.comsumer.queue.handle.impl;

import com.study.comsumer.queue.handle.Handle;
import com.study.comsumer.queue.handle.HandleInfo;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/8/4 23:12
 */
@Service
@HandleInfo(subscribe = {"test"})
public class OtherHandle implements Handle<ApplicationContext> {

    @Override
    public void handle(ApplicationContext msg) {
        logger.info("handle msg {}", msg);
    }
}
