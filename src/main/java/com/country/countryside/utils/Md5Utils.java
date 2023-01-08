package com.country.countryside.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 加密工具类
 */
public class Md5Utils {

    /**
     * 返回加密后的密文
     * @param password
     * @return
     */
    public static String md5(String password){
        return DigestUtils.md5Hex(password);
    }

/*    public static void main(String[] args){
        System.out.println(md5("123456"));
    }*/
}
