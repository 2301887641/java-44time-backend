package com.time.article.common.enums;

import com.time.exception.enums.BaseEnum;

/**
 * 通用模块使用的枚举
 *
 * @author suiguozhen on 18/11/14
 */
public enum CommonEnum implements BaseEnum {
    /**
     * 验证码
     */
    CAPTCHA(1100, "验证码生成失败");

    private Integer ordinal;
    private String label;

    CommonEnum(Integer ordinal, String label) {
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
