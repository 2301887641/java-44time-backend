package com.time.utils.core;


import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author suiguozhen on 18/08/28
 */
public class StringUtils {

    private static final String UNDERLINE = "_";

    /**
     * 判断字符串是否为空或空串
     * @param sequence
     * @return
     */
    public static boolean isEmpty(final CharSequence sequence) {
        return Objects.isNull(sequence) || (sequence.length() == 0);
    }

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

    /**
     * 正则匹配
     *
     * @param regex
     * @param content
     * @return
     */
    public static Matcher matcher(String regex, String content) {
        Pattern compile = Pattern.compile(regex);
        return compile.matcher(content);
    }
}
