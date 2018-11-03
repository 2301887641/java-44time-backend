package com.time.article.security.browser.config;

import com.time.article.security.core.code.api.CustomUserDetailsService;
import com.time.article.security.core.code.config.VerificationCodeSecurityConfig;
import com.time.article.security.core.code.mobile.SmsAuthenticationSecurityConfig;
import com.time.article.security.core.constants.SecurityConstants;
import com.time.article.security.core.defaults.DefaultCustomUserDetailServiceImpl;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * spring security安全配置
 * EnableConfigurationProperties让SecurityProperties这个配置类生效
 *
 * @author suiguozhen on 18/09/12
 */
//@Configuration
//@EnableConfigurationProperties(SecurityProperties.class)
public class BrowserSecurityConfig extends LoginSecurityConfig {
    /**
     * 安全配置属性
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**自定义用户登录处理*/
    @Autowired
    private CustomUserDetailsService unificationUserDetailService;

    /**导入自定义的验证码配置*/
    @Autowired
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    /**
     * 验证码相关配置
     */
    @Autowired
    private VerificationCodeSecurityConfig verificationCodeSecurityConfig;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    /**
     * 配置security 所有的配置都需要放入这里不能单独 否则不起作用
     * 1.formLogin说明是form表单登陆
     * 2.loginProcessingUrl 配置处理登陆方法的url 之前是/login
     * 3.loginPage 配置登录页面 我们设置为动态的
     * 4.antMatchers 登陆页面全部过滤掉
     * 5.successHandler 表单登陆成功后的处理
     * 6.cors 跨域必写
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        applyPasswordAuthenticationConfig(http);
        /**
         * 先过图片验证码
         */
        http.apply(verificationCodeSecurityConfig).
                and().
                apply(smsAuthenticationSecurityConfig).
                and().
                apply(socialSecurityConfig).
                and().
                rememberMe().
                tokenRepository(persistentTokenRepository()).
                tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()).
                userDetailsService(unificationUserDetailService).
                and().
                authorizeRequests().
                antMatchers(
                        "/add",
                        "/login/qq",
                        "/qq/login",
                        "/wechat/login",
                        "/add2",
                        SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                        SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX+"/*",
                        "/social/user",
                        "/user/qqRegistion"
                ).permitAll().
                anyRequest().
                authenticated().
                and().
                cors().and();
    }

    @Bean
    @ConditionalOnMissingBean(name = "unificationUserDetailService")
    public CustomUserDetailsService unificationUserDetailService(PasswordEncoder passwordEncoder) {
        DefaultCustomUserDetailServiceImpl defaultUserDetailServiceAdapter = new DefaultCustomUserDetailServiceImpl();
        defaultUserDetailServiceAdapter.setPasswordEncoder(passwordEncoder);
        return defaultUserDetailServiceAdapter;
    }

    /**
     * 配置rememberMe
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }


}