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
    public static final String DEFAULT_PARAMETER_NAME_CODE_CAPTCHA = "captcha";
    /**
     * 验证短信验证码时，http请求中默认的携带短信验证码信息的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_CODE_SMS = "sms";
    /**
     * 默认的处理验证码的url前缀
     */
    public static final String DEFAULT_VALIDATE_CODE_URL_PREFIX = "/code";
    /**默认的手机验证码登录请求处理url*/
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
}
