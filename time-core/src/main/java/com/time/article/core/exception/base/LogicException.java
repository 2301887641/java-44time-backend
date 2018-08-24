package com.time.article.core.exception.base;

import lombok.Getter;

/**
 * 运行时异常基类 分为:
 * ①业务异常businessException:有错误可以直接展示给客户看的错误  500错误
 * ②服务器异常serverException:后台数据或逻辑错误,只展示给前台看错误信息 不反馈给用户  400错误
 * 基类中使用@Getter注解后，其他子类都可以使用get方法获取code和msg
 * @author suiguozhen on 18/07/27
 */
@Getter
public  class LogicException extends RuntimeException {
    /**
     * 状态码
     */
    public Integer code;

    /**
     * 错误消息
     */
    public String msg;

    /**
     * 带有状态码和错误消息的构造函数
     */
    public LogicException(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }
}
