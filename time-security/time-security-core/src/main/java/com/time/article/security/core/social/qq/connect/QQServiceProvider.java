package com.time.article.security.core.social.qq.connect;

import com.time.article.security.core.social.qq.api.QQService;
import com.time.article.security.core.social.qq.api.QQServiceImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 *  QQServiceProvider
 *  appId：注册qq互联分配的id
 *  appSecret：注册qq互联的分配密码
 * @author suiguozhen on 18/09/29
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQService> {

    /**
     * 将用户导向qq的认证服务器的地址
     */
    private static final String QQ_URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /**
     * 	第三方拿着授权码获取Token的地址
     */
    private static final String QQ_URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    private String appId;

    /**
     * 	提供OAuth2Operations
     * @param appId
     * @param appSecret
     */
    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQ_URL_AUTHORIZE, QQ_URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    /**
     * 提供Api
     * @param accessToken
     * @return
     */
    @Override
    public QQService getApi(String accessToken) {
        return new QQServiceImpl(accessToken, appId);
    }
}
