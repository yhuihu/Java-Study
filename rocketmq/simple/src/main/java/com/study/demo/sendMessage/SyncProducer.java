package com.study.demo.sendMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author Tiger
 * @date 2019-12-23
 * @see com.study.demo.sendMessage
 **/
public class SyncProducer {
    /**
     * 发送同步消息：这种方式在发送消息之后需要等待broker返回发送的结果，因此会比较慢，但是可以保证可靠性
     * @param args args
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("shop-group");
        // 设置NameServer的地址
        producer.setNamesrvAddr("192.168.200.128:9876");
        // 启动Producer实例
        producer.start();
        for (int i = 0; i < 100; i++) {
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("shopTopic" ,"TagA" ,("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送消息到一个Broker
            SendResult sendResult = producer.send(msg);
            // 通过sendResult返回消息是否成功送达
            System.out.printf("%s%n", sendResult);
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();
    }
}
