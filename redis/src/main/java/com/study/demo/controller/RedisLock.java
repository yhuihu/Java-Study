package com.study.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tiger
 * @date 2020-03-10
 * @see com.study.demo.controller
 **/
@RestController
@Slf4j
public class RedisLock {

    public final String LOCK_PREFIX = "redis_lock";

    public final int LOCK_EXPIRE = 30000;

    private final String SUCCESS = "1";

    private RedisTemplate redisTemplate;

    @Autowired
    private RedisLock(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @GetMapping("lock")
    public String lock(@RequestParam(name = "id") String key) throws InterruptedException {
        String lock = LOCK_PREFIX + key;
        String uuid = UUID.randomUUID().toString();
        while (!getLock(lock, LOCK_PREFIX, LOCK_EXPIRE)) {
            Thread.sleep(100);
        }
        return "ok";
    }

    @GetMapping("unlock")
    public String unlock(@RequestParam(name = "id") String key) throws Exception {
        String lock = LOCK_PREFIX + key;
        AtomicInteger atomicInteger=new AtomicInteger(5);
        while (!releaseLock(lock, LOCK_PREFIX)) {
            if(atomicInteger.decrementAndGet()>0) {
                Thread.sleep(100);
            }else{
                throw new Exception("人数过多");
            }
        }
        return "ok";
    }

    /**
     * 获取锁
     *
     * @param lockKey         key
     * @param value           value
     * @param expireTime：单位-秒
     * @return Boolean
     */
    public Boolean getLock(String lockKey, String value, int expireTime) {
        try {
//            if redis.call('setNx',KEYS[1],ARGV[1])==1 then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end
            String script = "if redis.call('setNx',KEYS[1],ARGV[1])==1 then return redis.call('expire',KEYS[1],ARGV[2]) else return 0 end";
            RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
            Object result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), Collections.singletonList(lockKey), value, expireTime + "");
            System.out.println(result + "-----------");
            assert result != null;
            if (SUCCESS.equals(result.toString())) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 释放锁
     *
     * @param lockKey key
     * @param value   value
     * @return Boolean
     */
    public Boolean releaseLock(String lockKey, String value) {
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        RedisScript<Long> redisScript = new DefaultRedisScript<>(script, Long.class);
        Object result = redisTemplate.execute(redisScript, new StringRedisSerializer(), new StringRedisSerializer(), Collections.singletonList(lockKey), value);
        assert result != null;
        return SUCCESS.equals(result.toString());
    }
}
