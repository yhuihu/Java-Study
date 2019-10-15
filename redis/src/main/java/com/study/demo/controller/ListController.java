package com.study.demo.controller;

import com.study.demo.entity.School;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.controller
 **/
@RestController
@RequestMapping("List")
public class ListController {
    @Resource
    private RedisTemplate redisTemplate;
    private final String key = "school_list";

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        ListOperations<String, School> operations = redisTemplate.opsForList();
        operations.rightPush(key, school);
        Long length = operations.size(key);
        assert length != null;
        return Objects.requireNonNull(operations.range(key, length - 1, length)).get(0);
    }

    @GetMapping
    public List<School> allSchool() {
        ListOperations<String, School> operations = redisTemplate.opsForList();
        return operations.range(key,0,-1);
    }
}
