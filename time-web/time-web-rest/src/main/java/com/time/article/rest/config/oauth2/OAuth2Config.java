package com.time.article.rest.config.oauth2;

import com.time.qq.core.QQOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Oauth认证
 *
 * @author suiguozhen on 18/10/20
 */
@Configuration
public class OAuth2Config {

    @Value("${social.qq.appId}")
    private String appId;

    @Value("${social.qq.appSecret}")
    private String appSecret;

    @Value("${social.qq.callbackUrl}")
    private String callbackUrl;

    @Bean
    public QQOauth qqOauth() {
        return new QQOauth(appId, appSecret, callbackUrl);
    }
}
