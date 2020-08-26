package com.venusj.secondkill.redis;

public class MiaoshaUserKey extends BaseKey{

	public static final int TOKEN_EXPIRE = 3600*24 * 2;
	private MiaoshaUserKey(int expireSeconds, String prefix) {
		super(prefix, expireSeconds);
	}
	public static MiaoshaUserKey token = new MiaoshaUserKey(TOKEN_EXPIRE, "tk");
	public static MiaoshaUserKey getById = new MiaoshaUserKey(0, "id");
}
