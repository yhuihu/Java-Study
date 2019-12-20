package com.study.demo.handler;

import com.alibaba.fastjson.JSONObject;
import com.study.demo.entity.School;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Tiger
 * @date 2019-10-28
 * @see com.study.demo.handler
 **/
@Service
public class ConsumerListener {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @StreamListener(Sink.INPUT)
    //默认有ack机制，不抛出异常就是确认消费成功
    public void receive(String receiveMsg) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(receiveMsg);
            School school = JSONObject.parseObject(jsonObject.getString("school"), School.class);
            System.out.println(school);
        }catch (Exception e){
            throw new RuntimeException();
        }
    }
}
