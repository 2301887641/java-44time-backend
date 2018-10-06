package com.time.article.security.core.social.qq.connect;

import com.time.article.security.core.social.qq.api.QQService;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * 将之前写的
 * QQServiceProvider和QQAdapter传递进来创建
 * QQConnectionFactory
 * providerId：用户在服务商的唯一标示openId
 * @author suiguozhen on 18/09/29
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQService> {
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
