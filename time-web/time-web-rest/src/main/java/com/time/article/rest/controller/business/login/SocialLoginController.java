package com.time.article.rest.controller.business.login;

import com.time.qq.bean.QQAccessToken;
import com.time.qq.bean.QQInfo;
import com.time.qq.core.QQOauth;
import com.time.wechat.core.WechatOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方授权登陆
 * @author suiguozhen on 18/10/18
 */
@Controller
public class SocialLoginController {

    @Autowired
    private QQOauth qqOauth;

    @Autowired
    private WechatOauth wechatOauth;

    @RequestMapping("/qq/login")
    public String qqRedirect(HttpServletRequest request){
        return qqOauth.getAuthorizeURL(request);
    }

    @RequestMapping("/login/qq")
    public void qqCallback(HttpServletRequest request){
        QQAccessToken QQAccessToken = qqOauth.getOpenId(request);
        QQInfo userInfo = qqOauth.getUserInfo(QQAccessToken);

    }

    @RequestMapping("/wechat/login")
    public String wechatRedirect(HttpServletRequest request){
        return wechatOauth.getAuthorizeURL(request);
    }

    @RequestMapping("/Home/Index/wxlogin")
    public void wechatCallback(HttpServletRequest request){
//        QQAccessToken QQAccessToken = wechatOauth.getOpenId(request);
//         wechatOauth.getUserInfo(QQAccessToken);

    }

}
