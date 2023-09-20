package com.study.feign.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHello implements IFeignHello{
    @Override
    public void sayHi() {
        System.out.println("hi");
    }
}
