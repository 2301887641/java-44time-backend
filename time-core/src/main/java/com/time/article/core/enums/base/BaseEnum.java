package com.time.article.core.enums.base;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 枚举父类 统一实现接口
 * @author suiguozhen on 18/04/25
 */
public interface BaseEnum {
    /**
     * 获得标号
     * @return
     */
    Integer getOrdinal();

    /**
     * 获得字符串
     * @return
     */
    @JsonValue
    String getLabel();
}
