package com.time.exception.enums;

/**
 * 统一枚举信息
 * @author suiguozhen on 18/10/15
 */
public enum UnificationEnum implements BaseEnum{
    /**
     * 链接异常
     */
    CONNECTION(500, "http链接失败");

    private Integer ordinal;

    private String label;

    UnificationEnum(Integer ordinal, String label) {
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
