package com.time.article.security.core.social.config;

import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;
import java.util.Objects;

/**
 * 操作UserConnetion表的配置类
 * 配置getUsersConnectionRepository操作Connetion数据库的表
 * 在这里面可以定义创建表的前缀信息和存入数据库数据的加解密的方法
 * JdbcUsersConnectionRepository.sql中有建表的语句：
 * 其中重要的一些字段：
 * userId：业务系统Id
 * providerId:服务提供商的Id
 * providerUserId:用户在服务提供商的Id
 * @author suiguozhen on 18/09/29
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 不一定会实现
     */
    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository jdbc = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        /**
         * Encryptors.noOpText()不做加解密
         * 建表的前缀
         * jdbc.setTablePrefix("t_")
         */
        if(!Objects.isNull(connectionSignUp)){
            jdbc.setConnectionSignUp(connectionSignUp);
        }
        return jdbc;
    }

    /**
     *将SpringSocialFilter添加到安全配置的Bean
     * @return
     */
    @Bean
    public SpringSocialConfigurer socialSecurityConfig(){
        SpringSocialConfigurer configurer = new CustomSpringSocialConfigurer(securityProperties.getSocial().getFilterProcessesUrl());
        configurer.signupUrl(securityProperties.getSocial().getQq().getSignupUrl());
        return configurer;
    }

    /**
     * 配置providerSignInUtils工具用来获取session中的social信息
     * @param connectionFactoryLocator
     * @return
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator connectionFactoryLocator){
        return new ProviderSignInUtils(connectionFactoryLocator,getUsersConnectionRepository(connectionFactoryLocator));
    }
}
