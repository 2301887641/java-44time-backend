package com.time.article.security.core.social.wechat.connect;

import com.time.article.security.core.social.wechat.api.WeChatService;
import com.time.article.security.core.social.wechat.pojo.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信 api适配器，将微信 api的数据模型转为spring social的标准模型。
 * @author suiguozhen on 18/10/10
 */
public class WeChatAdapter implements ApiAdapter<WeChatService> {

    private String openId;

    public WeChatAdapter() {}

    public WeChatAdapter(String openId){
        this.openId = openId;
    }

    /**
     * @param api
     * @return
     */
    @Override
    public boolean test(WeChatService api) {
        return true;
    }

    /**
     * @param api
     * @param values
     */
    @Override
    public void setConnectionValues(WeChatService api, ConnectionValues values) {
        WeChatUserInfo profile = api.getUserInfo(openId);
        values.setProviderUserId(profile.getOpenid());
        values.setDisplayName(profile.getNickname());
        values.setImageUrl(profile.getHeadimgurl());
    }

    /**
     * @param api
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(WeChatService api) {
        return null;
    }

    /**
     * @param api
     * @param message
     */
    @Override
    public void updateStatus(WeChatService api, String message) {
        //do nothing
    }
}
