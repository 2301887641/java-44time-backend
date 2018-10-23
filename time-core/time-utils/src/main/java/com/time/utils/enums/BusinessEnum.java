package com.time.utils.enums;

import com.time.exception.enums.BaseEnum;

/**
 * 业务枚举信息类
 * 1000----1100
 * @author suiguozhen on 18/10/15
 */
public enum BusinessEnum implements BaseEnum {
    /**
     * 链接异常
     */
    CONNECTION(1000, "网络链接失败");

    private Integer ordinal;

    private String label;

    BusinessEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    /**
     * 获得标号 默认返回标号 rest请求时
     *
     * @return
     */
    @Override
    public Integer getOrdinal() {
        return ordinal;
    }

    /**
     * 获得字符串
     *
     * @return
     */
    @Override
    public String getLabel() {
        return label;
    }
}
