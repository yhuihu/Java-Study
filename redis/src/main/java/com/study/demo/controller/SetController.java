package com.study.demo.controller;

import com.study.demo.entity.School;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.controller
 **/
@RestController
public class SetController {
    @Resource
    private RedisTemplate redisTemplate;

    @PostMapping("Set")
    public Set<School> saveSchool(@RequestBody School school) {
        SetOperations<String, School> operations = redisTemplate.opsForSet();
        String key = "school_set";
        operations.add(key, school);
        return operations.members(key);
    }

    @PostMapping("ZSet")
    public Set<School> addSchool(@RequestBody School school) {
        ZSetOperations<String, School> operations = redisTemplate.opsForZSet();
        String key = "school_zset";
        operations.add(key, school, school.getId());
        return operations.range(key, 0, -1);
    }
}
