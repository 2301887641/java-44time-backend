package com.time.article.security.core.code.captcha.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author suiguozhen on 18/09/21
 */
public class CodeException extends AuthenticationException {
    public CodeException(String msg) {
        super(msg);
    }
}