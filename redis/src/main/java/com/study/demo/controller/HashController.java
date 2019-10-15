package com.study.demo.controller;

import com.study.demo.entity.School;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Tiger
 * @date 2019-10-15
 * @see com.study.demo.controller
 **/
@RestController
@RequestMapping("Hash")
public class HashController {
    @Resource
    private RedisTemplate redisTemplate;
    private final String key = "school_hash";

    @PostMapping
    public School saveSchool(@RequestBody School school) {
        HashOperations<String, String, School> operations = redisTemplate.opsForHash();
        operations.put(key, school.getId().toString(), school);
        return operations.get(key, school.getId().toString());
    }

    @GetMapping("{id}")
    public School getSchool(@PathVariable Long id) {
        HashOperations<String, String, School> operations = redisTemplate.opsForHash();
        return operations.get(key, id.toString());
    }

    @GetMapping
    public List<School> allSchool() {
        HashOperations<String, String, School> operations = redisTemplate.opsForHash();
        return operations.values(key);
    }

    @DeleteMapping("{id}")
    public void deleteSchool(@PathVariable Long id) {
        HashOperations<String, String, School> operations = redisTemplate.opsForHash();
        Boolean aBoolean = operations.hasKey(key, id.toString());
        assert aBoolean != null;
        if(aBoolean){
            operations.delete(key,id.toString());
        }
    }
}
