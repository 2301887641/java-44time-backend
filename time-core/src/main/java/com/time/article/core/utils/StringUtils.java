package com.time.article.core.utils;

/**
 * 字符串工具类
 *
 * @author suiguozhen on 18/08/28
 */
public class StringUtils {
    private static final String UNDERLINE = "_";

    /**
     * 字符串首字符大写
     *
     * @param str
     * @return
     */
    public static String firstToUpperCase(String str) {
        return new StringBuilder().append(Character.toUpperCase(str.toCharArray()[0])).append(str.substring(1)).toString();
    }

    /**
     * 格式化字段名 将字段中除第一个外 带有下划线的改成大写  log_type => logType
     *
     * @param name
     * @return
     */
    public static String uppercaseStringIgnoreFirst(String name) {
        if (name.contains(UNDERLINE)) {
            String[] splits = name.split(UNDERLINE);
            StringBuilder stringBuilder = new StringBuilder(splits[0]);
            for (int i = 1; i < splits.length; i++) {
                stringBuilder.append(StringUtils.firstToUpperCase(splits[i]));
            }
            return stringBuilder.toString();
        }
        return name;
    }
}
