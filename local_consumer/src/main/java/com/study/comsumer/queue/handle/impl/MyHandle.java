package com.study.comsumer.queue.handle.impl;

import com.study.comsumer.queue.handle.Handle;
import com.study.comsumer.queue.handle.HandleInfo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/8/3 23:15
 */
@Service
@HandleInfo(subscribe = {"test"})
public class MyHandle implements Handle<Map<String, String>> {

    @Override
    public void handle(Map<String, String> msg) {
        logger.info("handle msg {}", msg);
    }

}
