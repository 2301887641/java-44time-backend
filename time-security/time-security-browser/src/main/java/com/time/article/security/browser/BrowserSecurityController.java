package com.time.article.security.browser;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.WebUtils;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 统一登陆跳转 控制器
 *
 * @author suiguozhen on 18/09/17
 */
@Controller
public class BrowserSecurityController {
    @Autowired
    private SecurityProperties securityProperties;

    /**springSecurity自带的重定向类*/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 登陆页面转发 get请求重定向跳转 rest返回401请求
     *
     * @param request
     * @return
     */
    @GetMapping("/authentication/loginPage")
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**判断如果不是ajax请求的话 直接重定向到登陆*/
        if(!WebUtils.isAjaxRequest(request)){
            redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
        }
        return Result.failed("请先登陆!");
    }
}