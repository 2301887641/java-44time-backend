package com.time.qq.enums;

import com.time.exception.enums.BaseEnum;

/**
 * 业务枚举信息类
 * 1200-1300
 *
 * @author suiguozhen on 18/10/18
 */
public enum BusinessEnum implements BaseEnum {
    /**
     * 属性文件打开失败
     */
    IOEXCEPTION(1200, "属性文件打开失败"),
    //默认异常
    DEFAULT_EXCEPTION(1201,"默认异常");

    private Integer ordinal;
    private String label;

    BusinessEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    @Override
    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
