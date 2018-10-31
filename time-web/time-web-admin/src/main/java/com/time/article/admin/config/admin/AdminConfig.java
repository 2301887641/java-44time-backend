package com.time.article.admin.config.admin;

import com.time.qq.core.QQOauth;
import com.time.wechat.core.WechatOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * admin模块前端配置
 * @author suiguozhen on 18/10/31
 */
@Configuration
public class AdminConfig {
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

    /**
     * 配置qq登陆
     * @return
     */
    @Bean
    public QQOauth qqOauth() {
        return new QQOauth(qqAppId, qqAppSecret, qqCallbackUrl);
    }

    /**
     * 配置微信登陆
     * @return
     */
    @Bean
    public WechatOauth wechatOauth(){
        return new WechatOauth(wechatAppId,wechatAppSecret,wechatCallbackUrl);
    }


}
