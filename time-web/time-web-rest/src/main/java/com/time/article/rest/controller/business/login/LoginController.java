package com.time.article.rest.controller.business.login;

import com.time.article.core.message.result.Result;
import com.time.article.service.dto.business.user.UserDto;
import com.time.utils.core.HttpUrlConnectionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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