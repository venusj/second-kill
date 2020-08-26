package com.venusj.secondkill.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
@Data
@AllArgsConstructor
public class CodeMsg {

    private Integer code;

    private String message;

    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    public static CodeMsg REQUEST_ILLEGAL = new CodeMsg(500102, "请求非法");
    public static CodeMsg ACCESS_LIMIT_REACHED= new CodeMsg(500104, "访问太频繁！");
    /**
     * 用户 5002XX
     */
    public static CodeMsg SESSION_ERROR = new CodeMsg(500200, "session失效或者不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500210, "密码错误");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500201, "密码不能为空");
    public static CodeMsg MOBILE_EMPTY = new CodeMsg(500202, "手机号不能为空");
    public static CodeMsg MOBILE_ERROR = new CodeMsg(500203, "手机号格式错误");
    public static CodeMsg MOBILE_NOT_EXISTS = new CodeMsg(500204, "手机号不存在");

    // 商品 5003XX

    // 订单 5004XX

    // 秒杀 5005XX
    public static CodeMsg MIAO_SHA_OVER = new CodeMsg(500500, "商品已经秒杀完毕");
    public static CodeMsg REPEATE_MIAOSHA = new CodeMsg(500501, "不能重复秒杀");

    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.message, args);
        return new CodeMsg(code, message);
    }
}
