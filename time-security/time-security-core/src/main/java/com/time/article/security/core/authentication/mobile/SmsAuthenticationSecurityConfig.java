package com.time.article.security.core.authentication.mobile;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;

/**
 * 短信验证码配置类 此配置既要在浏览器使用也会在app使用
 * 俩模块都需要引这个类
 * @author suiguozhen on 18/10/04
 */
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {


    @Override
    public void configure(HttpSecurity builder) throws Exception {
        super.configure(builder);
    }
}
