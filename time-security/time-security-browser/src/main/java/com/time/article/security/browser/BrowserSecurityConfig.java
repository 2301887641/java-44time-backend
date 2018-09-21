package com.time.article.security.browser;

import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * spring security安全配置
 * EnableConfigurationProperties让SecurityProperties这个配置类生效
 * @author suiguozhen on 18/09/12
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;

    /**密码加密处理类 让security验证密码 可以实现自己的MD5等*/
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置security
     * 1.formLogin说明是form表单登陆
     * 2.loginProcessingUrl 配置处理登陆方法的url 之前是/login
     * 3.loginPage 配置登录页面 我们设置为动态的
     * 4.antMatchers 登陆页面全部过滤掉
     * 5.successHandler 表单登陆成功后的处理
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().
                loginProcessingUrl("/authentication/form").
                loginPage("/authentication/loginPage").
                successHandler(browserAuthenticationSuccessHandler).
                failureHandler(browserAuthenticationFailureHandler).
                and().
                authorizeRequests().
                antMatchers("/authentication/loginPage", securityProperties.getBrowser().getLoginPage()).permitAll().
                anyRequest().
                authenticated().
                and().
                csrf().disable();
    }
}