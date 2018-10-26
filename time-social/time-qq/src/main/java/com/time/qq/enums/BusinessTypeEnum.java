package com.time.qq.enums;

import com.time.exception.enums.BaseEnum;

/**
 * 业务类型枚举
 *
 * @author suiguozhen on 18/10/26
 */
public enum BusinessTypeEnum implements BaseEnum {
    /**
     * 业务类型
     */
    OPENID(1, "openid"),
    UNIONID(2, "unionid");

    private Integer ordinal;
    private String label;

    BusinessTypeEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    @Override
    public Integer getOrdinal() {
        return ordinal;
    }

    @Override
    public String getLabel() {
        return label;
    }
}
