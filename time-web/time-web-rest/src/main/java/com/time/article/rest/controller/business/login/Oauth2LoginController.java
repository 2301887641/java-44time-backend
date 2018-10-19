package com.time.article.rest.controller.business.login;

import com.time.qq.core.QQOauth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 第三方授权登陆
 * @author suiguozhen on 18/10/18
 */
@Controller
public class Oauth2LoginController {

    @Value("${social.qq.appId}")
    private String appId;

    @Value("${social.qq.appSecret}")
    private String appSecret;

    @Value("${social.qq.callbackUrl}")
    private String callbackUrl;

    @RequestMapping("/qq/login")
    public String qqRedirect(){
        QQOauth qqOauth = new QQOauth(this.appId, this.appSecret, this.callbackUrl);
        return qqOauth.getRedirectUrl();
    }

    @RequestMapping("/login/qq")
    public void qqCallback(HttpServlet,String code){
        System.out.println(code);
    }


}
