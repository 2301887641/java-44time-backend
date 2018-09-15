package com.time.article.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * spring security安全配置
 * @author suiguozhen on 18/09/12
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                formLogin().
                loginPage("/login.html").
                and().
                authorizeRequests().
                antMatchers("/login.html").permitAll().
                anyRequest().
                authenticated();
    }
}
