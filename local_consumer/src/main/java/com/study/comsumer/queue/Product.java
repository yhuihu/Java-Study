package com.study.comsumer.queue;

import com.study.comsumer.queue.template.AbstractQueueTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2022/7/30 23:06
 */
@Service
public class Product {

    @Autowired
    Map<String, AbstractQueueTemplate> templateMap;

    public void addMsg(Object map) throws InterruptedException {
        for (AbstractQueueTemplate value : templateMap.values()) {
            // 添加消息
            value.addMsg(map);
        }
    }
}
