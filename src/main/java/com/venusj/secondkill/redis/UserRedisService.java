package com.venusj.secondkill.redis;

import com.venusj.secondkill.entity.User;
import com.venusj.secondkill.utils.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
@Component
public class UserRedisService {

    @Autowired

    private RedisUtils redisUtils;

    public void set(KeyPrefix keyPrefix, Integer id, User user, Long expire)
    {
        String realKey = keyPrefix.getPrefix() + id;
        if(expire <= 0)
        {
            redisUtils.set(realKey, user);
        }
    }

    public User get(KeyPrefix keyPrefix, Integer id)
    {
        String realKey = keyPrefix.getPrefix() + id;
        Object result = redisUtils.get(realKey);
        if(result!= null)
        {
            return (User) result;
        }
        return null;
    }
}
