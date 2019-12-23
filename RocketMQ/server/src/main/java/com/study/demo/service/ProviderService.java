package com.study.demo.service;

import com.study.demo.source.MySource;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

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
//        source.output1().send(MessageBuilder.withPayload(message).setHeader(RocketMQHeaders.TAGS,"test").build());
        Random r = new Random();
        source.output1().send(MessageBuilder.withPayload(message).setHeader("index",r.nextInt(1000)+500).build());
//        mqTemplate.sendMessageInTransaction("demo-group","test",MessageBuilder.withPayload(message).build(),null);
    }

}
