package com.venusj.secondkill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author zhangjh
 * @date 2020/8/25
 * @desc
 */
public class MD5Utils {

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    private static final String salt = "1a2b3c4d";

    /**
     * 固定salt加密 -- 客户端
     *
     * @param src
     * @return
     */
    public static String inputBymd5(String src) {
        String pass = "" + salt.charAt(0) + salt.charAt(2) + src + salt.charAt(5) + salt.charAt(4);
        return md5(pass);
    }

    /**
     * 写入数据库加密
     *
     * @param src
     * @param salt
     * @return
     */
    public static String toDbBymd5(String src, String salt) {
        String pass = "" + salt.charAt(0) + salt.charAt(2) + src + salt.charAt(5) + salt.charAt(4);
        return md5(pass);
    }

    public static String inputToDbByMd5(String input, String dbSalt) {
        String inputBymd5 = inputBymd5(input);
        String result = toDbBymd5(inputBymd5, dbSalt);
        return result;
    }


    public static void main(String[] args) {
        System.out.println(inputBymd5("123456"));
        System.out.println(toDbBymd5(inputBymd5("123456"), "1a2b3c4d"));
        System.out.println(inputToDbByMd5("123456", "1a2b3c4d"));
    }
}
