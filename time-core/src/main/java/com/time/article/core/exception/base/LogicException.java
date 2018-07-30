package com.time.article.core.exception.base;

import lombok.Getter;

/**
 * 运行时异常类
 * @author suiguozhen on 18/07/27
 */
@Getter
public abstract class LogicException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer code;

    /**
     * 错误信息
     */
    private String msg;

    /**有参构造*/
    public LogicException(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
