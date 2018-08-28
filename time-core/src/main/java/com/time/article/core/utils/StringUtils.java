package com.time.article.core.utils;

/**
 * 字符串工具类
 * @author suiguozhen on 18/08/28
 */
public class StringUtils {
    /**
     * 字符串首字符大写
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str){
        return new StringBuilder().append(Character.toUpperCase(str.toCharArray()[0])).append(str.substring(1)).toString();
    }
}
