package com.time.article.admin.config.security;

import com.time.article.admin.constants.SecurityConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security配置
 * @author suiguozhen on 18/10/30
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 访问配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.
               formLogin().
               loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL).
               loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM).
               and().
               authorizeRequests().
               antMatchers(SecurityConstants.DEFAULT_LOGIN_PAGE_URL).permitAll().
               anyRequest().
               authenticated().
               and().
               cors().and().
               csrf().disable();
    }

    /**
     * 密码加密处理类 让security验证密码 可以实现自己的MD5等
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
