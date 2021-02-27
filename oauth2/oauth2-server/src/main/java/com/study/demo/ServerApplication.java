package com.study.demo;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;

/**
 * @author Tiger
 * @date 2020-10-11
 * @see com.study.demo
 **/
//@SpringBootApplication
//@MapperScan("com.study.demo.mapper")
public class ServerApplication {
    public static void main(String[] args) {
//        SpringApplication.run(ServerApplication.class, args);
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.addApplicationListener(new ApplicationListener<ContextStartedEvent>() {
            @Override
            public void onApplicationEvent(ContextStartedEvent applicationEvent) {
                System.out.println(" 1 接收到事件: " + applicationEvent);
            }
        });
// 就这样啊 没了啊  纯java呢
        context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
            @Override
            public void onApplicationEvent(ApplicationEvent applicationEvent) {
                System.out.println(" 2 接收到事件: " + applicationEvent);
            }
        });

        context.register(ServerApplication.class);
        context.refresh();

        ApplicationEventMulticaster bean = context.getBean(ApplicationEventMulticaster.class);

        context.publishEvent(new ServerApplication());

        context.start();

        context.close();
    }
    @EventListener
    public void lister(ContextClosedEvent contextClosedEvent){
        System.out.println(" 3 接收到事件: " + contextClosedEvent);
    }

    @EventListener
    public void lister2(ServerApplication serverApplication){
        System.out.println(" 4 接收到事件: " + serverApplication);
    }


 //可以自己发布 没了啊                          不用spring呢
}
