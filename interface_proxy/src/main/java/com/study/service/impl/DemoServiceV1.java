package com.study.service.impl;

import com.study.service.DemoService;

/**
 * @author yanghuihu
 * @version 1.0.0
 * @description TODO
 * @date 2023/6/4 19:46
 */
public class DemoServiceV1 implements DemoService {
    @Override
    public void sayHello() {
        System.out.println("say hello version 1");
    }
}
