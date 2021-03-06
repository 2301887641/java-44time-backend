package com.time.social.common.core;

import com.time.social.common.bean.Token;
import com.time.social.common.bean.UserInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * oauth第三方统一接口
 *
 * @author suiguozhen on 18/10/18
 */
public abstract class Oauth {
    /**
     * 开放平台授权地址
     *
     * @return
     */
    public abstract String getAuthorizeURL(HttpServletRequest request);

    /**
     * 根据request获取access token
     *
     * @param request
     * @return
     */
    protected abstract Token getAccessTokenByRequest(HttpServletRequest request);

    /**
     * 获取openId 唯一确定用户 多应用除外
     *
     * @param request
     * @return
     */
    public abstract Token getOpenId(HttpServletRequest request);

    /**
     * 获取unionid 多个应用可以唯一确定用户
     *
     * @param request
     * @return
     */
    public abstract Token getUnionId(HttpServletRequest request);

    /**
     * 获取qq用户信息
     * @param token
     * @return
     */
    public abstract UserInfo getUserInfo(Token token);
}
