package com.time.article.security.core.captcha.handler;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author suiguozhen on 18/09/21
 */
public class CaptchaException extends AuthenticationException {
    public CaptchaException(String msg) {
        super(msg);
    }
}