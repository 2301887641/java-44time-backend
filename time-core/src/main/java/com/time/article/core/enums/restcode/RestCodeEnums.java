package com.time.article.core.enums.restcode;

import lombok.Getter;

/**
 * rest枚举返回
 * feature: 使用的使用需要同时使用code和info才可以 不要单独使用,那样不如使用常量
 * @author suiguozhen on 18/04/14
 */

@Getter
public enum RestCodeEnums {
    /*操作成功*/
    SUCCESS(200,"操作成功"),
    /**验证码错误*/
    CAPTCHA_ERROR(500,"验证码错误"),
    /**
     * dao层错误
     */
    RECORD_MISSED(500, "不存在此记录"),
    /**
     * 自定义注解异常
     */
    ANNOTATION_EXCEPTION(500,""),
    /**
     * Aop异常
     * ①动态数据源aop中异常
     * ②访问日志aop中异常
     * */
    AOP_DYNAMIC_EXCEPTION(3303,"aop切换动态数据源错误"),
    AOP_ACCESS_LOG_EXCEPTION(3304,"aop记录访问日志错误"),

    /**默认异常*/
    DEFAULT_EXCEPTION(500,"服务器异常");

    private int code;
    private String info;

    RestCodeEnums(int code,String info){
        this.code=code;
        this.info=info;
    }

    @Override
    public String toString() {
        return info;
    }
}
