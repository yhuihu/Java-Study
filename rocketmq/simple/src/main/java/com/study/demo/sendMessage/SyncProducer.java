package com.study.demo.sendMessage;

import lombok.SneakyThrows;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Tiger
 * @date 2019-12-23
 * @see com.study.demo.sendMessage
 **/
public class SyncProducer {

    private static ExecutorService executorService;
    private static DefaultMQProducer producer = new DefaultMQProducer("study-group");

    static {
        executorService = Executors.newFixedThreadPool(10);
        // 实例化消息生产者Producer
        // 设置NameServer的地址
        producer.setNamesrvAddr("127.0.0.1:9876");
        // 启动Producer实例
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送同步消息：这种方式在发送消息之后需要等待broker返回发送的结果，因此会比较慢，但是可以保证可靠性
     *
     * @param args args
     * @throws Exception e
     */
    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 1; i++) {
            executorService.execute(new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        // 创建消息，并指定Topic，Tag和消息体
                        Message msg = new Message("studyTopic", "TagA", ("Hello RocketMQ " + Thread.currentThread().getName() + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                        // 发送消息到一个Broker
                        SendResult sendResult = producer.send(msg);
//                        throw new Exception("test");
                        // 通过sendResult返回消息是否成功送达
                        System.out.printf("%s%n", sendResult);
                    }
                }
            });
        }
        // 如果不再发送消息，关闭Producer实例。
//        producer.shutdown();
    }
}
