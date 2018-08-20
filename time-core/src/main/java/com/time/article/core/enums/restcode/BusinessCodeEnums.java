package com.time.article.core.enums.restcode;


import com.time.article.core.enums.base.BaseEnum;
import lombok.Getter;

/**
 * 业务枚举返回
 * feature: 使用的使用需要同时使用code和info才可以 不要单独使用,那样不如使用常量
 *
 * @author suiguozhen on 18/04/14
 */
@Getter
public enum BusinessCodeEnums implements BaseEnum {
    /**
     * dao层异常
     */
    DAO_RECORD_MISSED(500, "该记录已不存在"),
    /**
     * 树形
     */
    TREE_DISABLE_DELETE_CHILDREN(500,"不能删除带有子类的资源");

    private Integer ordinal;
    private String label;

    BusinessCodeEnums(Integer ordinal, String label) {
        this.ordinal = ordinal;
        this.label = label;
    }
}
