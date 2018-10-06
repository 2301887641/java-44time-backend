package com.time.article.security.core.social.qq.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.security.core.social.qq.api.QQService;
import com.time.article.security.core.social.qq.pojo.QQUserInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 *  获取用户信息接口的实现
 *  AbstractOAuth2ApiBinding中accessToken是存放前面5步获取的用户信息的
 *  每个用户的用户信息都不相同，因此QQImpl是个多实例的
 *  因此不能将此类申明成为Spring的一个组件 需要的时候new就可以了。
 *
 *  需要的参数：
 *  appId：注册qq互联分配的appid
 *  openId：qq用户的Id
 *  accessToken：父类提供
 *
 * @author suiguozhen on 18/09/29
 */
public class QQServiceImpl extends AbstractOAuth2ApiBinding implements QQService {
    /**
     * qq获取openid的url
     */
    private static final String QQ_OPENID_URL = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /**
     * 获取用户信息的url
     */
    private static final String QQ_USER_INFO_URL = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";
    /**
     * 应用的appId
     */
    private String appId;
    /**
     * 应用的openId
     */
    private String openId;
    /**
     * jackson压缩工具
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 构造函数
     *
     * @param accessToken
     * @param appId
     */
    public QQServiceImpl(String accessToken, String appId) {
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(QQ_OPENID_URL, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(QQ_USER_INFO_URL, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        QQUserInfo qqUserInfo;
        try {
            qqUserInfo = objectMapper.readValue(result, QQUserInfo.class);
            qqUserInfo.setOpenId(openId);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
        return qqUserInfo;
    }
}
