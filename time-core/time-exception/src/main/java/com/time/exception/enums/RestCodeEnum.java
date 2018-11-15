package com.time.exception.enums;

/**
 * 业务枚举返回
 * feature: 使用的使用需要同时使用code和info才可以 不要单独使用,那样不如使用常量
 * 400: 返回给前端看 但不要展示给用户看
 * 500: 返回给前端看 可以直接给用户看
 * @author suiguozhen on 18/04/14
 */
public enum RestCodeEnum implements BaseEnum {
    /*操作成功*/
    SUCCESS(200,"操作成功"),
    /**
     * dao层异常
     */
    DAO_RECORD_MISSED(400, "该记录已不存在"),
    /**注解异常*/
    ANNOTATION_BE_USED_TO_FUNC(400,"注解只能用于方法上"),
    /**
     * 树形结构异常
     */
    TREE_DISABLE_DELETE_CHILDREN(500,"不能删除带有子类的资源"),
    TREE_UNABLE_SET_CURRENT_PARENT(500,"不能将父类的子类设置成为当前类的父类"),
    TREE_UNABLE_SET_PARENT_AS_SELF(500,"不能将自己设为上级资源"),
    /**
     * 验证相关
     */
    MOBILE_MISSING(500,"请输入手机号"),
    /**默认异常*/
    DEFAULT_EXCEPTION(500,"服务器异常");

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
