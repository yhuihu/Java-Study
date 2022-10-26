package com.study.comsumer.queue.template;

import com.study.comsumer.queue.handle.Handle;
import com.study.comsumer.queue.handle.HandleInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/7/30 23:06
 */
public abstract class AbstractQueueTemplate<T> implements Runnable {

    volatile Thread thread;

    static Object LOCK_OBJ = new Object();

    public AbstractQueueTemplate() {

    }

    public AbstractQueueTemplate(Integer maxCount) {
        this.maxCount = maxCount;
    }

    public AbstractQueueTemplate(List<Handle<T>> handleList) {
        this.sortHandleList(handleList);
        this.handleList = handleList;
    }

    public List<Handle<T>> handleList = new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(AbstractQueueTemplate.class);

    /**
     * 存储消息的队列
     */
    private LinkedBlockingQueue<T> queue = new LinkedBlockingQueue<>();

    /**
     * 队列最大条数
     */
    private Integer maxCount = 10000;

    List<String> subscribeList = new ArrayList<>();

    public synchronized void handle() throws InterruptedException {
        T pop;
        while (true) {
            // 没有消息会阻塞，减少空跑
            pop = queue.take();
            for (Handle<T> tHandle : handleList) {
                tHandle.handle(pop);
            }
            //线程被中断，跳出循环，线程停止   个人认为应当保证消息被消费完整
            if (Thread.currentThread().isInterrupted() && queue.size() == 0) {
                break;
            }
        }
    }

    /**
     * 压入消息
     *
     * @param target 消息体
     */
    public synchronized void addMsg(T target) throws InterruptedException {

        if (!verifyMsg(target)) {
            return;
        }

        verifyThread();

        if (queue.size() > maxCount) {
            logger.error("queue count is full");
            return;
        }
        queue.put(target);
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

    void sortHandleList(List<Handle<T>> list, String... args) {
        List<Handle<T>> handleList = new ArrayList<>();
        for (Handle<T> tHandle : list) {
            HandleInfo annotation = tHandle.getClass().getAnnotation(HandleInfo.class);
            String[] subscribe = annotation.subscribe();
            List<String> tmpSubscribeList = Arrays.asList(subscribe);
            for (String arg : args) {
                if (tmpSubscribeList.contains(arg)) {
                    handleList.add(tHandle);
                    // 添加关注消息
                    this.subscribeList.add(arg);
                }
            }
        }
        sortHandleList(handleList);

        this.handleList = handleList;
    }

    /**
     * 责任链排序
     *
     * @param list 消息处理实现类
     */
    void sortHandleList(List<Handle<T>> list) {
        list.sort(Comparator.comparingInt(Handle::getOrder));
    }

    /**
     * 校验线程是否开始跑了，懒汉
     */
    private void verifyThread() {
        if (thread == null) {
            synchronized (LOCK_OBJ) {
                if (thread == null) {
                    logger.info("{} thread is not start , begin to init thread", this.getClass().getName());
                    thread = new Thread(this);
                    thread.setDaemon(true);
                    thread.start();
                    logger.info("{} thread is start", this.getClass().getName());
                }
            }
        }
    }

    /**
     * 校验消息是否需要进行处理
     *
     * @param msg
     * @return
     */
    boolean verifyMsg(T msg) {
        Map<String, Object> map = (Map<String, Object>) msg;
        return subscribeList.contains(map.get("subscribe"));
    }
}
