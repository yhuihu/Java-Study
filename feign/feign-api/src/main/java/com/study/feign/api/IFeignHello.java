package com.study.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "feign-nacos-provider-modules")
public interface IFeignHello {

    @RequestMapping(value = "sayHi", method = RequestMethod.GET)
    void sayHi();


}
