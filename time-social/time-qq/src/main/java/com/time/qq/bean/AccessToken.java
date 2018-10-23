package com.time.qq.bean;

import com.time.social.common.bean.BaseAccessToken;

/**
 * access token实体类
 *
 * @author suiguozhen on 18/10/23
 */
public class AccessToken extends BaseAccessToken {

    public AccessToken() {
    }

    public AccessToken(String accessToken, String expireIn, String refreshToken) {
        this.setAccessToken(accessToken);
        this.setExpireIn(expireIn);
        this.setRefreshToken(refreshToken);
    }
}