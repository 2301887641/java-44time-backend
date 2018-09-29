package com.time.article.security.core.social.qq.connect;

import com.time.article.security.core.social.qq.api.QQService;
import com.time.article.security.core.social.qq.impl.QQServiceImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * @author suiguozhen on 18/09/29
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {
    private static final String QQ_URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String QQ_URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQ_URL_AUTHORIZE, QQ_URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQService getApi(String accessToken) {
        return new QQServiceImpl(accessToken, appId);
    }
}
