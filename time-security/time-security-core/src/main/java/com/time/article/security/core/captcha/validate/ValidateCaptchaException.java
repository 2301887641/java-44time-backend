package com.time.article.security.core.captcha.validate;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常类
 * @author suiguozhen on 18/09/21
 */
public class ValidateCaptchaException extends AuthenticationException {

    public ValidateCaptchaException(String msg) {
        super(msg);
    }
}
