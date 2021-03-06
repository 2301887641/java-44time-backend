package com.time.exception.enums;

/**
 * 业务枚举返回
 * 101: 返回给前端看 但不要展示给用户看
 * 500: 返回给前端看 可以直接给用户看
 * @author suiguozhen on 18/04/14
 */
public enum RestCodeEnum implements BaseEnum {
    /*操作成功*/
    SUCCESS(200,"操作成功"),
    /**
     * 自定义异常 显示给前台
     */
    EXCEPTION(500,"请求错误"),
    /**
     * 服务器异常  前台统一提示 后台异常即可
     */
    FAILURE(101,"服务器异常");

    private Integer ordinal;
    private String label;

    RestCodeEnum(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
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
