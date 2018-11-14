package com.time.manager.captcha;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author suiguozhen on 18/11/14
 */
@Configuration
@ConditionalOnWebApplication
public class CaptchaConfig {
    @Bean
    public DefaultKaptcha defaultKaptcha() {
        return  new DefaultKaptcha();
    }
}