package com.time.social.common.core;

import com.sun.net.httpserver.HttpServer;
import com.time.social.common.bean.BaseAccessToken;

import javax.servlet.http.HttpServletRequest;

/**
 * oauth第三方统一接口
 * @author suiguozhen on 18/10/18
 */
public interface Oauth {
    /**
     * 开放平台授权地址
     * @return
     */
    String getAuthorizeURL();

    /**
     * 根据request获取access token
     * @param request
     * @return
     */
    BaseAccessToken getAccessTokenByRequest(HttpServletRequest request);

    /**
     * 获取openId 唯一确定用户 多应用除外
     * @return
     */
    BaseAccessToken getOpenId(HttpServletRequest request);
}
