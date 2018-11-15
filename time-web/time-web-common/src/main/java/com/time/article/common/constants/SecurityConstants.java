package com.time.article.common.constants;

/**
 * 安全常量配置
 *
 * @author suiguozhen on 18/10/31
 */
public interface SecurityConstants {
    /**
     * 默认的用户名密码登录请求处理url
     */
    public static final String DEFAULT_LOGIN_PROCESSING_URL_FORM = "/authentication/form";
    /**
     * 默认登录页面
     */
    public static final String DEFAULT_LOGIN_PAGE_URL = "/login";
    /**
     * 验证码
     */
    public static final String DEFAULT_CAPTCHA_URL = "/captcha";
    /**
     * css
     */
    public static final String DEFAULT_RESOURCE_CSS = "/css/**";
    /**
     * img
     */
    public static final String DEFAULT_RESOURCE_IMG = "/img/**";
    /**
     * js
     */
    public static final String DEFAULT_RESOURCE_JS = "/js/**";
    /**
     * 用户名或密码错误
     */
    public static final String USERNAME_OR_PASSWORD_MISSED = "用户名或密码错误";
    /**
     * post请求
     */
    public static final String DEFAULT_POST_REQUEST = "post";
}
