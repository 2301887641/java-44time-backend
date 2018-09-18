package com.time.article.core.message.constant;

/**
 * 定义公共常量
 *
 * @author suiguozhen on 18/07/05
 */
public interface Constants {
    /**
     * -----------------结果集返回--------------------
     * 信息提示相关
     * ①200默认请求成功
     * ②500默认请求失败
     * ③默认提示信息
     */
    Integer RESULT_SUCCESS_CODE = 200;
    Integer RESULT_FAILED_CODE = 500;
    String RESULT_SUCCESS_INFO = "成功";

    /**
     * -----------------树形结构相关---------------------
     * ①父类id
     */
    Integer TREE_PARENT_ID = 0;

    /**
     * ------------------数据源-------------------------
     * ①主数据源
     * ②次数据源
     */
    String DATASOURCE_MASTER__NAME = "master";
    String DATASOURCE_SECONDARY_NAME = "secondary";

    /**
     * ------------------Aop执行顺序-------------------
     * ①Aop多数据源执行顺序
     * ②Aop Spring事务执行顺序
     */
    int AOP_DYNAMIC_DATASOURCE_ORDER = 1;
    int AOP_TRANSACTION_ORDER = 2;

    /**
     * -------------------自定义日志注解描述-----------------------------
     */
    String CUSTOM_ANNOTATION_MISSING = "未填写";
}
