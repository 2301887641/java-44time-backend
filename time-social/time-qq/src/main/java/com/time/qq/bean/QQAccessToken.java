package com.time.qq.bean;

import com.time.social.common.bean.Token;

/**
 * access token实体类
 *
 * @author suiguozhen on 18/10/23
 */
public class QQAccessToken extends Token {

    public QQAccessToken() {
    }

    public QQAccessToken(String accessToken, String expireIn, String refreshToken) {
        this.setAccessToken(accessToken);
        this.setExpireIn(expireIn);
        this.setRefreshToken(refreshToken);
    }
}