package com.study.demo.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.study.demo.entity.School;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.Message;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Tiger
 * @date 2019-10-16
 * @see com.study.demo.handler
 **/
@RocketMQTransactionListener(txProducerGroup = "tx-demo-group")
public class ProducerListener implements RocketMQLocalTransactionListener {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    @Transactional
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object arg) {
        //消息发送成功回调此方法，此方法执行本地事务
        try {
            //解析消息内容
            String jsonString = new String((byte[]) message.getPayload());
            JSONObject jsonObject = JSONObject.parseObject(jsonString);
            School school = JSONObject.parseObject(jsonObject.getString("school"), School.class);
            jdbcTemplate.update("insert into school (id,cityName,name,description) values (?,?,?,?)", school.getId(), school.getCityName(), school.getName(), school.getDescription());
            System.out.println(school);
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
            return RocketMQLocalTransactionState.ROLLBACK;
        }
    }

    //此方法检查事务执行状态
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        RocketMQLocalTransactionState state;
        final JSONObject jsonObject = JSON.parseObject(new String((byte[]) message.getPayload()));
        School school = JSONObject.parseObject(jsonObject.getString("school"), School.class);
        //事务id
        String txNo = school.getId();
        School school1 = jdbcTemplate.queryForObject("selsct * from school where id = ?", School.class, txNo);
//        log.info("回查事务，事务号: {} 结果: {}", accountChangeEvent.getTxNo(),isexistTx);
        System.out.println("check");
        System.out.println(jsonObject);
        if (school1 == null) {
            state = RocketMQLocalTransactionState.COMMIT;
        } else {
            state = RocketMQLocalTransactionState.UNKNOWN;
        }

        return state;

    }
}
