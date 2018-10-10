package com.time.article.security.core.social.wechat.connect;

import com.time.article.security.core.social.wechat.api.WeChatService;
import com.time.article.security.core.social.wechat.api.WeChatServiceImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 微信的OAuth2流程处理器的提供器，供spring social的connect体系调用
 * @author suiguozhen on 18/10/10
 */
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChatService> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";
    /**
     * 微信获取accessToken的url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    /**
     * @param appId
     * @param appSecret
     */
    public WeChatServiceProvider(String appId, String appSecret) {
        super(new WeChatOAuth2Template(appId, appSecret,URL_AUTHORIZE,URL_ACCESS_TOKEN));
    }

    @Override
    public WeChatService getApi(String accessToken) {
        return new WeChatServiceImpl(accessToken);
    }
}
