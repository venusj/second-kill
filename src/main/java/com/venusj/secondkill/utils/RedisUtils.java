package com.venusj.secondkill.utils;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created By zhangjh
 * Company 7qb
 * desc: redis工具类
 * on 2020/8/18 11:23
 */
@Component
@AllArgsConstructor
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * hash get
     *
     * @param key
     * @param item
     * @return
     */
    public Object hashGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 设置hashkey对应的多个键值
     *
     * @param key
     * @return
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取hashkey对应的所有键值
     *
     * @param key
     * @return
     */
    public Map<Object, Object> hmget(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * hash set
     *
     * @param key
     * @param item
     * @param value
     * @return
     */
    public boolean hashSet(String key, String item, Object value) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * hash set 带过期时间
     *
     * @param key
     * @param item
     * @param value
     * @param time
     * @return
     */
    public boolean hashSet(String key, String item, Object value, Long time) {
        try {
            redisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * string 过期时间
     *
     * @param key
     * @param time
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key
     * @return
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * string set
     *
     * @param key
     * @param value
     * @return
     */
    public Boolean set(final String key, Object value) {
        boolean flag = false;

        try {
            ValueOperations<String, Object> forValue = redisTemplate.opsForValue();
            forValue.set(key, value);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * string set 过期时间
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    public Boolean setEx(final String key, Object value, Long expire) {
        boolean flag = false;

        try {
            ValueOperations<String, Object> forValue = redisTemplate.opsForValue();
            forValue.set(key, value);
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * string get
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object o = valueOperations.get(key);
        return o;
    }

    /**
     * 删除
     *
     * @param key
     * @return
     */
    public Boolean remove(final String key) {
        if (exists(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    /**
     * 递增
     *
     * @param key
     * @param delta
     * @return
     */
    public long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key
     * @param delta
     * @return
     */
    public long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }

        return redisTemplate.opsForValue().increment(key, -delta);
    }


    /**
     * 入队
     *
     * @param key
     * @param value
     * @return
     */
    public long leftPush(String key, Object value) {

        return redisTemplate.opsForList().leftPush(key, value);

    }

    /**
     * 向队列头部添加全部集合元素
     *
     * @param key
     * @param list
     * @return
     */
    public long leftPushAll(String key, List<Object> list) {

        return redisTemplate.opsForList().leftPushAll(key, list);

    }

    /**
     * 统计队列中所有的元素数量
     *
     * @param key
     * @return
     */
    public long size(String key) {
        return redisTemplate.opsForList().size(key);
    }


    /**
     * 返回队列中起始位置到结束为止的集合元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<Object> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 出队
     *
     * @param key
     * @return
     */
    public Object rightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 弹出队列最新元素
     *
     * @param key
     * @return
     */
    public Object leftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 删除队列所有元素
     *
     * @param key
     */
    public void deleteAll(String key) {
        redisTemplate.opsForList().trim(key, 0, 0);
        redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 向集合中增加元素
     *
     * @param key
     * @param value
     * @return
     */
    public Long setAdd(String key, Object value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    /**
     * 批量增加集合
     *
     * @param key
     * @param list
     * @return
     */
    public long setAddAll(String key, List list) {
        return redisTemplate.opsForSet().add(key, list);
    }

    /**
     * 获取多个集合的交集
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set<Object> setInter(String key1, String key2) {
        return redisTemplate.opsForSet().intersect(key1, key2);
    }

    /**
     * 获取两个集合的差集
     *
     * @param key1
     * @param key2
     * @return
     */
    public Set setDiff(String key1, String key2) {
        return redisTemplate.opsForSet().difference(key1, key2);
    }
}
