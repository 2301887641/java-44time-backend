package com.time.article.rest.controller.business.login;

import com.time.qq.config.QQConnectConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Properties;

/**
 * 第三方授权登陆
 * @author suiguozhen on 18/10/18
 */
@Controller
public class Oauth2LoginController {

    @RequestMapping("/login/qq")
    public String qqRedirect(){

        return "111";
    }




}
