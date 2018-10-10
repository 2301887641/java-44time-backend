package com.time.article.security.core.social.wechat.api;

import com.time.article.security.core.social.wechat.pojo.WeChatUserInfo;

/**
 * 微信服务
 * @author suiguozhen on 18/10/10
 */
public interface WeChatService {
    /**
     * 获取用户信息
     * 这里微信需要根据参数获取,而qq不需要
     * 微信走完oauth流程后,除了拿到accessToken还会拿到一个openId
     * @param openId
     * @return
     */
    WeChatUserInfo getUserInfo(String openId);
}
