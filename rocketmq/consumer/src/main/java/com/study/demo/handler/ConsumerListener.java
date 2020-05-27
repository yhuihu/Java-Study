package com.study.demo.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Tiger
 * @date 2019-10-28
 * @see com.study.demo.handler
 **/
@Service
public class ConsumerListener {

    @Autowired
    JdbcTemplate jdbcTemplate;
    private static int count1 = 0;
    @Value("${server.port}")
    private String port;

//    @StreamListener(Sink.INPUT)
//    //默认有ack机制，不抛出异常就是确认消费成功
//    public void receive(String receiveMsg) {
//        try {
////            JSONObject jsonObject = JSONObject.parseObject(receiveMsg);
////            School school = JSONObject.parseObject(jsonObject.getString("school"), School.class);
////            String sql = "delete from school where id='" + school.getId() + "'";
////            jdbcTemplate.execute(sql);
////            addCount();
//            System.out.println(receiveMsg);
//        } catch (Exception e) {
//            throw new RuntimeException();
//        }
//    }

    private synchronized void addCount() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            count1++;
            System.out.println(port + "---count:" + count1);
        } catch (Exception e) {
            throw new RuntimeException();
        } finally {
            lock.unlock();
        }
    }
}
