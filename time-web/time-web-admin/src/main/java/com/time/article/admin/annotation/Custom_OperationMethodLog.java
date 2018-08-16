package com.time.article.admin.annotation;

import com.time.article.dao.enums.business.log.LogEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义log注解
 * @author suiguozhen on 18/07/12
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Custom_OperationMethodLog {
    /**表示具体的哪个模块 带上crud前缀*/
    String value();
    /**区分类型 crud   LOG_SELECT:查询或删除 lOG_UPDATE:添加或修改 */
    LogEnum type() default LogEnum.LOG_SELECT;
}
