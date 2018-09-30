package com.time.article.security.browser.config;

import com.time.article.security.browser.handler.BrowserUserDetailServiceImpl;
import com.time.article.security.core.code.captcha.handler.CaptchaFilter;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
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
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * 安全配置属性
     */
    @Autowired
    private SecurityProperties securityProperties;
    /**
     * browser成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler browserAuthenticationSuccessHandler;
    /**
     * browser失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    /**自定义用户登录处理*/
    @Autowired
    private UserDetailsService browserUserDetailService;

    @Autowired
    private SpringSocialConfigurer socialSecurityConfig;

    /**
     * 配置rememberMe
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    /**
     * 密码加密处理类 让security验证密码 可以实现自己的MD5等
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 配置security
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
        CaptchaFilter filter = new CaptchaFilter();
        filter.setBrowserAuthenticationFailureHandler(browserAuthenticationFailureHandler);
        filter.setSecurityProperties(securityProperties);
        filter.afterPropertiesSet();

        http.
                addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class).
                formLogin().
                loginProcessingUrl("/authentication/form").
                loginPage("/authentication/loginPage").
                successHandler(browserAuthenticationSuccessHandler).
                failureHandler(browserAuthenticationFailureHandler).
                and().
                apply(socialSecurityConfig).
                and().
                rememberMe().
                tokenRepository(persistentTokenRepository()).
                tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds()).
                userDetailsService(browserUserDetailService).
                and().
                authorizeRequests().
                antMatchers(
                        "/authentication/loginPage",
                        securityProperties.getBrowser().getLoginPage(),
                        "/captcha",
                        "/sms"
                ).permitAll().
                anyRequest().
                authenticated().
                and().
                cors().and().
                csrf().disable();
    }

    @Bean
    @ConditionalOnMissingBean(name = "browserUserDetailService")
    public UserDetailsService browserUserDetailService() {
        return new BrowserUserDetailServiceImpl();
    }
}