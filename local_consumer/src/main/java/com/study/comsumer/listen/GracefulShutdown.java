package com.study.comsumer.listen;

import com.study.comsumer.queue.template.AbstractQueueTemplate;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description 优雅停机
 * @date 2022/8/14 22:36
 */
@Component
public class GracefulShutdown implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent contextClosedEvent) {
        Map<String, AbstractQueueTemplate> beansOfType = contextClosedEvent.getApplicationContext().getBeansOfType(AbstractQueueTemplate.class);
        Collection<AbstractQueueTemplate> values = beansOfType.values();

        for (AbstractQueueTemplate value : values) {
            try {
                value.handle();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
