package com.study.demo.sendMessage;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author Tiger
 * @date 2019-12-23
 * @see com.study.demo.sendMessage
 **/
public class AsyncProducer {
    public static void main(String[] args) throws Exception {
        // 实例化消息生产者Producer
        DefaultMQProducer producer = new DefaultMQProducer("syncProducer");
        // 设置NameServer的地址
        producer.setNamesrvAddr("my.service.com:9876");
        // 启动Producer实例
        producer.start();
//        producer.setRetryTimesWhenSendAsyncFailed(0);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            // 创建消息，并指定Topic，Tag和消息体
            Message msg = new Message("AsyncProducer", "TagA", "OrderID188", "Hello world".getBytes(RemotingHelper.DEFAULT_CHARSET));
            // SendCallback接收异步返回结果的回调
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index,
                            sendResult.getMsgId());
                }
                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        // 这里需要注意，由于使用了异步，这么有可能会导致连接已经shutdown了才返回异步结果，这样会报错，因此只能手动关闭
//        producer.shutdown();
    }
}
