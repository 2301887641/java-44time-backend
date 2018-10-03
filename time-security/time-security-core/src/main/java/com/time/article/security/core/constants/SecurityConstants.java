package com.time.article.security.core.constants;

/**
 * 安全相关 常量
 *
 * @author suiguozhen on 18/10/03
 */
public interface SecurityConstants {
    /**
     * 验证图片验证码时，http请求中默认的携带图片验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_CAPTCHA = "captchaCode";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "smsCode";
    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
}
