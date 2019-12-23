package com.study.demo.service;

import com.study.demo.source.MySource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.service
 **/
@Service
public class ProviderService {

    @Resource
    RocketMQTemplate mqTemplate;
    @Autowired
    MySource source;
    public void send(String message) {
        source.output1().send(MessageBuilder.withPayload(message).build());
//        mqTemplate.sendMessageInTransaction("demo-group","test",MessageBuilder.withPayload(message).build(),null);
    }

}
