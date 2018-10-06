package com.time.article.security.browser.config;

import com.time.article.security.core.constants.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 抽取登陆安全配置类
 *
 * @author suiguozhen on 18/10/05
 */
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler unificationAuthenticationSuccessHandler;
    /**
     * 失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler unificationAuthenticationFailureHandler;

    /**
     * 密码相关的配置
     *
     * @param http
     * @throws Exception
     */
    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.
                formLogin().
                loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL).
                loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM).
                successHandler(unificationAuthenticationSuccessHandler).
                failureHandler(unificationAuthenticationFailureHandler);
    }
}
