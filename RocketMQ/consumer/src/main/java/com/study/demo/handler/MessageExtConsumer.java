package com.study.demo.handler;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.common.UtilAll;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;

/**
 * @author Tiger
 * @date 2020-01-21
 * @see com.study.demo.handler
 **/
@RocketMQMessageListener(topic = "TransactionTopic", consumerGroup = "demo-group", selectorExpression = "tag")
public class MessageExtConsumer implements RocketMQListener<MessageExt>, RocketMQPushConsumerLifecycleListener {

    @Override

    // 实现消息的消费处理

    public void onMessage(MessageExt message) {

        System.out.printf("------- MessageExtConsumer received message, msgId:%s, body:%s %n ", message.getMsgId(), new String(message.getBody()));

    }

    @Override

    // 设置从当前时间点开始消费消息

    public void prepareStart(DefaultMQPushConsumer consumer) {

        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_TIMESTAMP);
        consumer.setConsumeTimestamp(UtilAll.timeMillisToHumanString3(System.currentTimeMillis()));

    }

}
