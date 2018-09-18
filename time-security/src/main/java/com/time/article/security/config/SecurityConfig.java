package com.time.article.security.config;

import com.time.article.security.properties.UnificationSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security安全配置
 *
 * @author suiguozhen on 18/09/12
 */
@Configuration
@EnableConfigurationProperties(UnificationSecurityProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnificationSecurityProperties unificationSecurityProperties;

    /**
     * 配置security
     * 1.formLogin说明是form表单登陆
     * 2.loginProcessingUrl 配置处理登陆方法的url
     * 3.loginPage 配置登录页面 我们设置为动态的
     * 4.antMatchers 登陆页面全部过滤掉
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().
                loginProcessingUrl("/authentication/form").
                loginPage("/authentication/loginPage").
                and().
                authorizeRequests().
                antMatchers("/authentication/loginPage", unificationSecurityProperties.getBrowser().getLoginPage()).permitAll().
                anyRequest().
                authenticated().
                and().
                csrf().disable();
    }
}
