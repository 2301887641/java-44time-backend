package com.time.article.security.controller;

import com.time.article.core.message.result.Result;
import com.time.article.core.utils.WebUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 统一登陆跳转 控制器
 *
 * @author suiguozhen on 18/09/17
 */
@Controller
public class UnificationSecurityController {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 身份认证 跳转到这里
     *
     * @param request
     * @return
     */
    @GetMapping("/authentication/loginPage")
    public Result loginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (!WebUtils.isAjaxRequest(request)) {
            redirectStrategy.sendRedirect(request, response, "/login.html");
        }
        return Result.failed("请先登陆!");
    }
}