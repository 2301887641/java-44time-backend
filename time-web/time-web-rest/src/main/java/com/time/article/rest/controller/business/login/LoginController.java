package com.time.article.rest.controller.business.login;

import com.time.article.core.message.result.Result;
import com.time.article.service.dto.business.user.UserDto;
import com.time.utils.core.HttpUrlConnectionUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * 第三方登陆
 * @author suiguozhen on 18/07/03
 */
@Api(tags = {"后台登陆、验证码"}, description = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private ProviderSignInUtils providerSignInUtils;

    /**
     * qq注册
     * @param userDto
     * @param request
     * @return
     */
    @RequestMapping("/user/qqRegistion")
    public Result thirdPartyRegistion(UserDto userDto, HttpServletRequest request){
        /**
         * 需要获取用户唯一标识 插入数据库和social中 这里数据库先不插入
         */
        String username = userDto.getUsername();
        providerSignInUtils.doPostSignUp(username,new ServletWebRequest(request));
        return Result.success();
    }

    @RequestMapping("/")
    public Result index(){
        return Result.success("首页");
    }

    @RequestMapping("/add")
    public Result add(){
        HttpUrlConnectionUtils.get("https://graph.qq.com/oauth2.0/authorize");
        return Result.success();
    }

    @RequestMapping("/add2")
    public Result add2(){
        return Result.success();
    }
}