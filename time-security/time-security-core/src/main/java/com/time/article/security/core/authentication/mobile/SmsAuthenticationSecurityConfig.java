package com.time.article.security.core.authentication.mobile;

import com.time.article.security.core.api.UserDetailsServiceAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * 短信验证码配置类 此配置既要在浏览器使用也会在app使用
 * 俩模块都需要引这个类
 * @author suiguozhen on 18/10/04
 */
@Component
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler unificationAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler unificationAuthenticationFailureHandler;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        /**
         * 先配置SmsAuthenticationFilter
         * 这里面必须要配置authenticationManager
         * 因为smsAuthenticationFilter要调用authenticationManager的方法做认证
         * AuthenticationManager从http对象中来
         */
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        /**把成功处理和失败处理统一起来*/
        smsAuthenticationFilter.setAuthenticationFailureHandler(unificationAuthenticationFailureHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(unificationAuthenticationSuccessHandler);
        /**配置SmsAuthenticationProvider*/
        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        smsAuthenticationProvider.setUserDetailsServiceAdapter((UserDetailsServiceAdapter) userDetailsService);
        /**加入到springSecurity安全框架里面去 把我们自己写的这个provider加进去*/
        http.authenticationProvider(smsAuthenticationProvider)
            .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}