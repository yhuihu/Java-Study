package com.study.comsumer.queue.template;

import com.study.comsumer.queue.handle.Handle;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/2/26 22:01
 */
public abstract class RedisQueueTemplate<T> extends AbstractQueueTemplate<T> {
    @Resource
    RedisTemplate redisTemplate;

    private String[] queueName;


    @Override
    public synchronized void handle() throws InterruptedException {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        T pop;
        while (true) {
            // 没有消息会阻塞，减少空跑
            for (String queue : queueName) {
                pop = setOperations.pop(queue);
                if (Objects.isNull(pop)) {
                    break;
                }
                for (Handle<T> tHandle : handleList) {
                    tHandle.handle(pop);
                }
            }
            Thread.sleep(5000L);
        }
    }

    @Override
    public synchronized void addMsg(T target) throws InterruptedException {
        SetOperations<String, T> setOperations = redisTemplate.opsForSet();
        for (String queue : queueName) {
            setOperations.add(queue, target);
        }
        verifyThread();
    }

    @Override
    void sortHandleList(List<Handle<T>> list, String... args) {
        this.queueName = args;
        super.sortHandleList(list, args);
    }
}
