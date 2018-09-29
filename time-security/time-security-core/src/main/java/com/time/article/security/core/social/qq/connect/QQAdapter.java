package com.time.article.security.core.social.qq.connect;

import com.time.article.security.core.social.qq.api.QQService;
import com.time.article.security.core.social.qq.pojo.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @author suiguozhen on 18/09/29
 */
public class QQAdapter implements ApiAdapter<QQService> {
    @Override
    public boolean test(QQService api) {
        return true;
    }

    @Override
    public void setConnectionValues(QQService api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        /**qq服务商的用户id*/
        values.setProviderUserId(userInfo.getOpenId());
        /**昵称*/
        values.setDisplayName(userInfo.getNickname());
        /**头像*/
        values.setImageUrl(userInfo.getFigureurl_qq_1());
        /**个人主页 qq不存在 微博个人主页url可以放入*/
        values.setProfileUrl(null);
    }

    @Override
    public UserProfile fetchUserProfile(QQService api) {
        return null;
    }

    /**
     * 首页相关的不必填写
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(QQService api, String message) {

    }
}
