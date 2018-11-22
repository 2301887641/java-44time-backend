package com.time.exception.constant;

/**
 * 常量池
 *
 * @author suiguozhen on 18/10/29
 */
public interface ConstantPool {
    /**
     * 字符串相关
     * 字符序列
     */
    String STRING_CHAR_SEQUENCE = "qwertyuiopasdfghjklzxcvbnm";
    /**
     * 响应相关
     * 响应类型
     */
    String RESPONSE_CONTENT_TYPE = "application/json;charset=utf-8";
    /**
     * 注解相关
     */
    String ANNOTATION_USED_FOR_FUNC = "注解只能用于方法上";
    /**
     * DAO相关
     */
    String DAO_RECORD_MISSED = "该记录已不存在";
    /**
     * 树形删除
     */
    String DAO_TREE_DELETE_CHILD_DISABLED="不能删除带有子类的资源";
    /**
     * 树形设置
     */
    String DAO_TREE_SET_SELF_PARENT="不能将自己设为上级资源";
}
