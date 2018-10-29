package com.time.article.rest.config.oauth2;

import com.time.qq.core.QQOauth;
import com.time.wechat.core.WechatOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Oauth登陆认证
 *
 * @author suiguozhen on 18/10/20
 */
@Configuration
public class SocialLoginConfig {

    @Value("${social.qq.appId}")
    private String qqAppId;

    @Value("${social.qq.appSecret}")
    private String qqAppSecret;

    @Value("${social.qq.callbackUrl}")
    private String qqCallbackUrl;

    @Value("${social.wechat.appId}")
    private String wechatAppId;

    @Value("${social.wechat.appSecret}")
    private String wechatAppSecret;

    @Value("${social.wechat.callbackUrl}")
    private String wechatCallbackUrl;

    @Bean
    public QQOauth qqOauth() {
        return new QQOauth(qqAppId, qqAppSecret, qqCallbackUrl);
    }

    @Bean
    public WechatOauth wechatOauth(){
        return new WechatOauth(wechatAppId,wechatAppSecret,wechatCallbackUrl);
    }
}
