package com.time.article.core.controller.annotation;

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
    /**区分类型 crud 1.查询或删除 2.添加或修改 */
    short type() default 1;
}
