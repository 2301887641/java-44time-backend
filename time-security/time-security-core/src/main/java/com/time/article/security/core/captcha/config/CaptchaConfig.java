package com.time.article.security.core.captcha.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 * @author suiguozhen on 18/06/06
 */
@Configuration
public class CaptchaConfig {

    @Bean(name="captchaProducer")
    public DefaultKaptcha getDefaultCaptcha(){
        DefaultKaptcha defaultCaptcha = new DefaultKaptcha();
        return defaultCaptcha;
    }
}
