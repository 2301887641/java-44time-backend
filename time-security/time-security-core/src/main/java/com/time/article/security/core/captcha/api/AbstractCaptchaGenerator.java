package com.time.article.security.core.captcha.api;

import com.time.article.security.core.captcha.pojo.Captcha;

/**
 * 抽象验证码类 应用级配置
 *
 * @author suiguozhen on 18/09/26
 */
public abstract class AbstractCaptchaGenerator {

    /**
     * 生成验证码图片
     *
     * @param width
     * @param height
     */
    public abstract Captcha buildCaptchaImage(String width, String height, String length, long expireIn);
}
