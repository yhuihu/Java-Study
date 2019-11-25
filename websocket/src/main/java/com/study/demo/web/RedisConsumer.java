package com.study.demo.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.demo.entity.MessageEntity;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.io.IOException;

/**
 * @author Tiger
 * @date 2019-11-25
 * @see com.study.demo.web
 **/
public class RedisConsumer implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] bytes1=message.getBody();
        String encoded = new String(bytes1).split(",",2)[1];
        ObjectMapper om = new ObjectMapper();
        try {
            MessageEntity messageEntity=om.readValue(encoded, MessageEntity.class);
            ChatHandler.redisSendToUser(messageEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
