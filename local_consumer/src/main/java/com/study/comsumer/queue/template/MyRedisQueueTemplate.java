package com.study.comsumer.queue.template;

import com.study.comsumer.queue.handle.Handle;
import com.study.comsumer.util.Constant;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/3/8 21:32
 */
@Service
@Order(Integer.MIN_VALUE)
public class MyRedisQueueTemplate extends RedisQueueTemplate<Map<String, String>> implements InitializingBean {
    @Autowired
    List<Handle<Map<String, String>>> list;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.sortHandleList(list, Constant.SCRIBE_NAME_TEST);
    }
}
