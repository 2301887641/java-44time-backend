package com.time.article.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 自定义认证异常 用于springSecurity中使用
 * @author suiguozhen on 18/11/16
 */
public class CustomizedAuthenticationException extends AuthenticationException {

    public CustomizedAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public CustomizedAuthenticationException(String msg) {
        super(msg);
    }
}
