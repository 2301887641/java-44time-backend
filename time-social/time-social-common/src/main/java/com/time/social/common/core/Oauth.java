package com.time.social.common.core;

import com.sun.net.httpserver.HttpServer;

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
    String getAccessTokenByRequest(HttpServletRequest request);
}
