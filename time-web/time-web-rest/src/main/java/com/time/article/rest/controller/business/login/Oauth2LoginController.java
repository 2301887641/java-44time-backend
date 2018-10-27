package com.time.article.rest.controller.business.login;

import com.time.qq.bean.AccessToken;
import com.time.qq.core.QQOauth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方授权登陆
 * @author suiguozhen on 18/10/18
 */
@Controller
public class Oauth2LoginController {

    @Autowired
    private QQOauth qqOauth;

    @RequestMapping("/qq/login")
    public String qqRedirect(){
        return qqOauth.getAuthorizeURL();
    }

    @RequestMapping("/login/qq")
    public void qqCallback(HttpServletRequest request){
        AccessToken accessToken = qqOauth.getOpenId(request);
        qqOauth.getUserInfo(accessToken);
    }


}
