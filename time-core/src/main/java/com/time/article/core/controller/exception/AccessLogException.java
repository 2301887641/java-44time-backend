package com.time.article.core.controller.exception;

import com.time.article.core.enums.restcode.RestCodeEnums;
import com.time.article.core.exception.base.LogicException;

/**
 * 自定义访问日志注解 异常
 *
 * @author suiguozhen on 18/08/13
 */
public class AccessLogException extends LogicException {
    /**
     * 有参构造
     *
     * @param code
     * @param msg
     */
    public AccessLogException(Integer code, String msg) {
        super(code, msg);
    }

    public AccessLogException() {
        super(RestCodeEnums.AOP_ACCESS_LOG_EXCEPTION.getCode(), RestCodeEnums.AOP_ACCESS_LOG_EXCEPTION.getInfo());
    }

    public AccessLogException(String msg) {
        super(RestCodeEnums.AOP_ACCESS_LOG_EXCEPTION.getCode(), msg);
    }
}
