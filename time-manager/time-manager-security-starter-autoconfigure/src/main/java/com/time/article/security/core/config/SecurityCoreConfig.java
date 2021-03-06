package com.time.article.security.core.config;

import com.time.article.security.core.service.DefaultUserDetailsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring security核心配置
 *
 * @author suiguozhen on 18/11/12
 */
@Configuration
public class SecurityCoreConfig {

    /**
     * 默认的用户认证实现  默认用户名随意  密码：123456
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "UnificationUserDetailService")
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        DefaultUserDetailsServiceImpl defaultUserDetailsService = new DefaultUserDetailsServiceImpl();
        defaultUserDetailsService.setPasswordEncoder(passwordEncoder);
        return defaultUserDetailsService;
    }
}
