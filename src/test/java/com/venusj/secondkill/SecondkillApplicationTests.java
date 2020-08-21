package com.venusj.secondkill;

import com.venusj.secondkill.entity.SecondKillUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SecondkillApplicationTests {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test()
    {
//        redisTemplate.opsForValue().set("zhang", 1);
//
//        Object o = redisTemplate.opsForValue().get("zhang");
//        System.out.println(o);

        redisTemplate.opsForHash().put("user:1", "id", 1 );
        redisTemplate.opsForHash().put("user:1", "name", "张金华" );
        redisTemplate.opsForHash().put("user:1", "age", 25);
    }

    @Test
    public void test02()
    {
        SecondKillUser object = new SecondKillUser()
                .setId(2)
                .setNickname("芋道源码")
                .setSalt("223.3");
        String key = String.format("user:%d", object.getId());
        redisTemplate.opsForValue().set(key, object);
    }

}
