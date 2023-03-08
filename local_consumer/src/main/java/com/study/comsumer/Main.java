package com.study.comsumer;


import com.study.comsumer.queue.Product;
import com.study.comsumer.util.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Main {
    public static void main(String[] args) throws InterruptedException {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class);
        Product bean = run.getBean(Product.class);
        Map<String, Object> tmpMap = new HashMap<>();
        tmpMap.put(Constant.SCRIBE,Constant.SCRIBE_NAME_TEST);
        bean.addMsg(tmpMap);
        Thread.sleep(1000000L);
    }
}
