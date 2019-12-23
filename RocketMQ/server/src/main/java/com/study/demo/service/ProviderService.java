package com.study.demo.service;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Source;
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
    Source source;
    public void send(String message) {
        source.output().send(MessageBuilder.withPayload(message).build());
//        mqTemplate.sendMessageInTransaction("demo-group","test",MessageBuilder.withPayload(message).build(),null);
    }

}
