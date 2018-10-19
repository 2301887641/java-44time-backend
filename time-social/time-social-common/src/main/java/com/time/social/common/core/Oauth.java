package com.time.social.common.core;

import com.sun.net.httpserver.HttpServer;

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


    String getAccessTokenByRequest();
}
