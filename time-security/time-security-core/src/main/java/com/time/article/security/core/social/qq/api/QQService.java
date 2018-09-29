package com.time.article.security.core.social.qq.api;

import com.time.article.security.core.social.qq.pojo.QQUserInfo;

/**
 * qq获取用户接口
 * @author suiguozhen on 18/09/29
 */
public interface QQService {
    /**
     * 获取用户信息
     * @return
     */
    QQUserInfo getUserInfo();
}
