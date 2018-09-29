package com.time.article.security.core.social.qq.config;

import com.time.article.security.core.properties.SecurityProperties;
import com.time.article.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @author suiguozhen on 18/09/29
 */
@Configuration
@ConditionalOnProperty(prefix = "custom.security.social.qq",name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(
                securityProperties.getSocial().getQq().getProviderId(),
                securityProperties.getSocial().getQq().getAppId(),
                securityProperties.getSocial().getQq().getAppSecret()
                );
    }
}
