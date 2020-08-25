package com.venusj.secondkill.redis;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
public abstract class BaseKey implements KeyPrefix{


    private String prefix;

    private int expireSeconds;

    // 默认0永不过期
    public BaseKey(String prefix) {
        this(prefix, 0);
    }

    public BaseKey(String prefix, int expireSeconds) {
        this.prefix = prefix;
        this.expireSeconds = expireSeconds;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    /**
     * 0 代表永不过期
     * @return
     */
    @Override
    public int expireSeconds() {
        return expireSeconds;
    }

    @Override
    public String getPrefix() {
        String simpleName = getClass().getSimpleName();
        return simpleName + ":" + prefix;
    }
}
