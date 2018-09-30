package com.time.article.security.core.config;

import com.time.article.security.core.code.generator.AbstractCodeGenerator;
import com.time.article.security.core.code.captcha.generator.DefaultCaptchaGenerator;
import com.time.article.security.core.code.sms.sender.DefaultSmsSender;
import com.time.article.security.core.code.sms.sender.SmsSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码配置
 *
 * @author suiguozhen on 18/06/06
 */
@Configuration
public class CodeConfig {

    /**
     * 如果已经存在captchaGenerator这个bean的话
     * 不会再实例化默认的验证码
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "captchaGenerator")
    public AbstractCodeGenerator captchaGenerator() {
        return new DefaultCaptchaGenerator();
    }

    /**
     * 短信发送器
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsSender.class)
    public SmsSender smsSender(){
        return new DefaultSmsSender();
    }
}