package com.venusj.secondkill.redis;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();
}
