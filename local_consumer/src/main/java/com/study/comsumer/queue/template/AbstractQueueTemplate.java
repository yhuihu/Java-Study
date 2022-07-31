package com.study.comsumer.queue.template;

import com.study.comsumer.queue.handle.Handle;
import com.sun.deploy.security.BlacklistedCerts;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/7/30 23:06
 */
public abstract class AbstractQueueTemplate<T> implements Runnable {

    public AbstractQueueTemplate() {

    }

    public AbstractQueueTemplate(Queue<T> queue, Integer maxCount) {
        this.queue = queue;
        this.maxCount = maxCount;
    }

    public List<Handle<T>> handleList = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(AbstractQueueTemplate.class);

    /**
     * 存储消息的队列
     */
    private Queue<T> queue = new LinkedBlockingQueue<>();

    /**
     * 队列最大条数
     */
    private Integer maxCount = 10000;

    public void handle() throws InterruptedException {
        // 队列没有消息，等待一段时间
        if (queue.size() <= 0) {
            this.wait(3000L);
        }
        T pop;
        while ((pop = pop()) != null) {
            for (Handle<T> tHandle : handleList) {
                tHandle.handle(pop);
            }
        }
    }

    /**
     * 压入消息
     *
     * @param target 消息体
     */
    public void add(T target) {
        if (queue.size() > maxCount) {
            logger.error("queue count is full");
            this.notifyAll();
            return;
        }
        queue.add(target);
        this.notifyAll();
    }

    /**
     * 获取消息
     */
    public T pop() {
        return queue.poll();
    }

    public void setQueue(Queue<T> queue) {
        this.queue = queue;
    }

    public void setMaxCount(Integer maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public void run() {
        try {
            // 永恒执行
            while (true) {
                this.handle();
            }
        } catch (InterruptedException e) {
            logger.error("handle throw error : ", e);
            throw new RuntimeException(e);
        }
    }
}
