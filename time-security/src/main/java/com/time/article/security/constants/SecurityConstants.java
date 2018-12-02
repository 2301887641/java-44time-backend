package com.time.article.security.constants;

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
     * 请填写验证码
     */
    public static final String CAPTCHA_LOSE = "请填写验证码";
    /**
     * 请先生成验证码
     */
    public static final String CAPTCHA_GENERATE_FIRST = "请先生成验证码";
    /**
     * 验证码不匹配
     */
    public static final String CAPTCHA_NOT_MATCH = "验证码不匹配";
    /**
     * 验证码已过期
     */
    public static final String CAPTCHA_EXPIRED = "验证码已过期";
    /**
     * post请求
     */
    public static final String DEFAULT_POST_REQUEST = "post";
    /**
     * 验证码在session中存储的名称
     */
    public static final String SESSION_CAPTCHA_NAME = "captcha";
    /**
     * 过期时间  单位秒
     */
    public static final Long VALIDATE_CODE_OVERTIME = 60L;
    /**
     * 图片验证码请求参数
     */
    public static final String PARAMETER_CAPTCHA = "captcha";
    /**
     * 需要放行的url
     * index
     */
    public static final String ANON_INDEX_URL = "/index";
    /**
     * 需要放行的静态后缀
     */
    public static final String ANON_HTML_SUFFIX = "*.html";
    /**
     * 需要放行的动态后缀
     */
    public static final String ANON_JHTML_SUFFIX = "*.jhtml";
    /**
     * 前台需要登陆才能访问的路径 会员中心
     */
    public static final String AUTHENTICATION_MEMBER_PREFIX = "/member/*";
    /**
     * 后台需要登陆才能访问的路径 后台管理中心
     */
    public static final String AUTHENTICATION_ADMIN_PREFIX = "/admin/*";
}
