package com.time.article.security.browser;

import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 统一登陆跳转 控制器
 *
 * @author suiguozhen on 18/09/17
 */
@Controller
public class BrowserSecurityController {

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登陆页面转发 get请求重定向跳转 rest返回401请求
     *
     * @return
     */
    @RequestMapping("/authentication/loginPage")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth instanceof AnonymousAuthenticationToken) {
            return securityProperties.getBrowser().getLoginPage();
//        }
//        return "/";
    }
}