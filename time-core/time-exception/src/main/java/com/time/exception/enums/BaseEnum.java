package com.time.exception.enums;

/**
 * 枚举父类 统一实现接口
 * @author suiguozhen on 18/04/25
 */
public interface BaseEnum {

    /**
     * 获得标号 默认返回标号 rest请求时
     * @return
     */
    Integer getOrdinal();

    /**
     * 获得字符串
     * @return
     */
    String getLabel();
}
