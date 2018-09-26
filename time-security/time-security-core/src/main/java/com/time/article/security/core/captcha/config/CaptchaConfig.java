package com.time.article.security.core.captcha.config;

import com.time.article.security.core.captcha.api.AbstractCaptchaGenerator;
import com.time.article.security.core.captcha.api.DefaultCaptchaGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author suiguozhen on 18/06/06
 */
@Configuration
public class CaptchaConfig {

    /**
     * 如果已经存在captchaGenerator这个bean的话
     * 不会再实例化默认的验证码
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "captchaGenerator")
    public AbstractCaptchaGenerator captchaGenerator() {
        return new DefaultCaptchaGenerator();
    }
}