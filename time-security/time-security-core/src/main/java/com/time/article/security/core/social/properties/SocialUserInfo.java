package com.time.article.security.core.social.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 社交用户信息
 *
 * @author suiguozhen on 18/10/08
 */
@Getter
@Setter
public class SocialUserInfo {
    /**
     * 哪个第三方用户
     */
    private String providerId;
    /**
     * 用户id
     */
    private String providerUserId;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 头像
     */
    private String headImg;

    public SocialUserInfo(String providerId, String providerUserId, String nickName, String headImg) {
        this.providerId = providerId;
        this.providerUserId = providerUserId;
        this.nickName = nickName;
        this.headImg = headImg;
    }
}