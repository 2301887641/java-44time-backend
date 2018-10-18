package com.time.article.security.core.social.wechat.config;

import com.time.article.security.core.properties.SecurityProperties;
import com.time.article.security.core.social.wechat.connect.WeChatConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * 微信登录配置
 * @author suiguozhen on 18/10/10
 */
@Configuration
@ConditionalOnProperty(prefix = "custom.security.social.weChat",name = "app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new WeChatConnectionFactory(
                securityProperties.getSocial().getWeChat().getProviderId(),
                securityProperties.getSocial().getWeChat().getAppId(),
                securityProperties.getSocial().getWeChat().getAppSecret());
    }
}
