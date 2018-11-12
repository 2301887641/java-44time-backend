package com.time.article.admin.config.security;

import com.time.article.common.constants.SecurityConstants;
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
public class AdminSecurityConfig extends WebSecurityConfigurerAdapter {

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
               antMatchers(
                       SecurityConstants.DEFAULT_LOGIN_PAGE_URL,
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
