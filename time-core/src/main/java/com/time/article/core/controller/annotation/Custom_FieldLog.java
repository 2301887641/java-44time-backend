package com.time.article.core.controller.annotation;


import com.time.article.core.message.constant.Constants;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义字段注解 记录日志
 *
 * @author suiguozhen on 18/08/13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Custom_FieldLog {
    String value() default Constants.CUSTOM_ANNOTATION_MISSING;
}
