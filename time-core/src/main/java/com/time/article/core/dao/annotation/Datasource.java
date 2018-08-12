package com.time.article.core.dao.annotation;

import com.time.article.core.message.constant.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据源注解
 * @author suiguozhen on 18/08/11
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Datasource {
    String value() default Constants.MASTER_DATASOURCE_NAME;
}
