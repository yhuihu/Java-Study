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
    public void receive(String receiveMsg) {
        JSONObject jsonObject = JSONObject.parseObject(receiveMsg);
        School school = JSONObject.parseObject(jsonObject.getString("school"), School.class);
        System.out.println(school);
    }
}
