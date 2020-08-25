package com.venusj.secondkill.redis;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
public class UserKey extends BaseKey {

    private UserKey(String prefix, int expireSeconds) {
        super(prefix, expireSeconds);
    }

    private UserKey(String prefix)
    {
        super(prefix);
    }

    public static UserKey getById = new UserKey("id");

    public static UserKey getByName = new UserKey("name");


}
