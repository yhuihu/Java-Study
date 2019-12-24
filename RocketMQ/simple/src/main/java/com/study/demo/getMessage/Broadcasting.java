package com.study.demo.getMessage;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author Tiger
 * @date 2019-12-23
 * @see com.study.demo.getMessage
 **/
public class Broadcasting {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者,指定组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("syncProducer");
        // 指定Namesrv地址信息.
        consumer.setNamesrvAddr("my.service.com:9876");
        // 订阅Topic
        consumer.subscribe("AsyncProducer", "*");
        //广播模式消费
        consumer.setMessageModel(MessageModel.BROADCASTING);
        // 注册回调函数，处理消息
        consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
            System.out.printf("%s Receive New Messages: %s %n",
                    Thread.currentThread().getName(), msgs);
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
//                                                            ConsumeConcurrentlyContext context) {
//                System.out.printf("%s Receive New Messages: %s %n",
//                        Thread.currentThread().getName(), msgs);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
        //启动消息者
        consumer.start();
        System.out.printf("Consumer Started.%n");
    }
}
