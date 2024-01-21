package com.study.demo.controller;

import com.study.demo.entity.School;
import com.study.demo.util.ScanOperationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.controller
 **/
@RestController
@RequestMapping("String")
public class StringController {
    @Resource
    private RedisTemplate<String, School> redisTemplate;

    @Autowired
    ScanOperationUtil operationUtil;

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        ValueOperations<String, School> operations = redisTemplate.opsForValue();
        String key = "school_" + school.getId();
        operations.set(key,school);
        redisTemplate.expire(key,1, TimeUnit.DAYS);
        return operations.get(key);
    }

    @GetMapping("{id}")
    public School getSchool(@PathVariable Long id){
        ValueOperations<String, School> operations = redisTemplate.opsForValue();
        String key="school_"+id;
        System.out.println(redisTemplate.getExpire(key));
        return operations.get(key);
    }

    @DeleteMapping("{id}")
    public void deleteSchool(@PathVariable Long id){
        String key="school_"+id;
        Boolean aBoolean = redisTemplate.hasKey(key);
        assert aBoolean != null;
        if (aBoolean){
            redisTemplate.delete(key);
        }
    }

    @GetMapping("/scan/{id}")
    public void scanSchool(@PathVariable Long id){
        operationUtil.scan("id");
    }
}
