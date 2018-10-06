package com.time.article.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author suiguozhen on 18/09/21
 */
public class VerificationCodeException extends AuthenticationException {
    public VerificationCodeException(String msg) {
        super(msg);
    }
}