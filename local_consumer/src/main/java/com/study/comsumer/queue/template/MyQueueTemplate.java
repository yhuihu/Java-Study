package com.study.comsumer.queue.template;

import com.study.comsumer.queue.handle.Handle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 本地测试用
 * @date 2022/8/3 23:34
 */
@Service
@Order(Integer.MIN_VALUE)
public class MyQueueTemplate extends AbstractQueueTemplate<Map<String, String>> implements InitializingBean {

    @Autowired
    List<Handle<Map<String, String>>> list;

    @Override
    public void afterPropertiesSet() throws Exception {
        this.sortHandleList(list, "test");
    }
}
