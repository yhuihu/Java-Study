package com.study.demo.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * @author Tiger
 * @date 2019-12-23
 * @see com.study.demo.source
 **/
public interface MySource {
    @Output("output1")
    MessageChannel output1();
}
