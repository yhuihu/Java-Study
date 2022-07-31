package com.study.comsumer.queue.handle;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/7/31 14:48
 */
public interface Handle<T> {

    void handle(T msg);

}
