package com.cao.xps.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisTest {
    @Autowired
    RedisTemplate redisTemplate;
    public void key(){
        try {
            redisTemplate.watch(redisTemplate);
            redisTemplate.multi();
            redisTemplate.opsForValue().set("name","peng");
            redisTemplate.opsForValue().set("age",123);
            redisTemplate.exec();

        }catch (Exception e){
            redisTemplate.discard();//放弃事务

        }finally {
            redisTemplate.unwatch();
        }
        redisTemplate.keys("*");


    }

    public void string(){
        redisTemplate.opsForValue().set("name","peng");
        redisTemplate.opsForValue().set("age","1");
        redisTemplate.opsForValue().get("name");
        redisTemplate.opsForValue().append("name","123");
        redisTemplate.opsForValue().increment("age",5);
        redisTemplate.opsForValue().decrement("age",3);

    }
}
