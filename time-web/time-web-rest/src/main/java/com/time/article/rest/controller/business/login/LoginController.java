package com.time.article.rest.controller.business.login;

import com.time.article.core.message.result.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 第三方登陆
 * @author suiguozhen on 18/07/03
 */
@Api(tags = {"后台登陆、验证码"}, description = "LoginController")
@RestController
public class LoginController {

    @RequestMapping("/authentication/require")
    public Result require(){
        return Result.failed("it seems to be disabled");
    }

    @RequestMapping("/")
    public Result index(){
        return Result.success("首页");
    }
}