package com.time.article.rest.constants;

/**
 * 安全常量配置
 * @author suiguozhen on 18/10/30
 */
public interface SecurityConstants {
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 登录页面 rest只能返回401 因为有默认访问页面相当于没有保护
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/authentication/require";
    /**
     * 默认的手机验证码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_MOBILE = "/authentication/mobile";
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
    /**
     * 发送短信验证码 或 验证短信验证码时，传递手机号的参数的名称
     */
    public static final String DEFAULT_PARAMETER_NAME_MOBILE = "mobile";
}
