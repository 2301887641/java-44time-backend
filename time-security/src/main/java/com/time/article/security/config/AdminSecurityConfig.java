package com.time.article.security.config;

import com.time.article.security.constants.SecurityConstants;
import com.time.article.security.filter.CaptchaFilter;
import com.time.article.security.core.handler.UnificationAuthenticationFailureHandler;
import com.time.article.security.core.handler.UnificationAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security配置
 * @author suiguozhen on 18/10/30
 */
@Configuration
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UnificationAuthenticationSuccessHandler unificationAuthenticationSuccessHandler;

    @Autowired
    private UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler;

    /**
     * 访问配置
     * @param http
     * 1.formLogin form表单登陆
     * 2.loginPage  登陆页面
     * 3.loginProcessingUrl 表单提交地址
     * 4.and 返回同一级
     * 5.authorizeRequests  所有请求都需要认证
     * 6.antMatchers   方法所使用的路径可能会包括Ant风格的通配符
     * 7.permitAll  无条件允许访问
     * 8.authorizeRequests  方法有很多子方法，每个子匹配器将会按照声明的顺序起作用
     * 9.anyRequest().authenticated 其他所有路径都需要权限校验
     * 10.csrf 跨站请求伪造防护 post请求默认的都开启了csrf的模式
     * 11.successHandler 成功处理器
     * 12.failureHandler 失败处理器
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.
               addFilterBefore(new CaptchaFilter(), UsernamePasswordAuthenticationFilter.class).
               formLogin().
               loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL).
               loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM).
               successHandler(unificationAuthenticationSuccessHandler).
               failureHandler(unificationAuthenticationFailureHandler).
               and().
               authorizeRequests().
               antMatchers(
                       SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
                       SecurityConstants.DEFAULT_CAPTCHA_URL,
                       SecurityConstants.DEFAULT_RESOURCE_CSS,
                       SecurityConstants.DEFAULT_RESOURCE_IMG,
                       SecurityConstants.DEFAULT_RESOURCE_JS
               ).permitAll().
               anyRequest().
               authenticated();
//               and().
               //以get方式退出系统
//               logout().logoutRequestMatcher(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PAGE_URL,"GET"));
    }

    /**
     * 密码加密处理类 让security验证密码 可以实现自己的MD5等
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
