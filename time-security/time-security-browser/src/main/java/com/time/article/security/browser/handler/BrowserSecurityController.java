package com.time.article.security.browser.handler;

import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

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
        //TODO 这里需要检查cookie然后跳转到首页上去index
//        Authentication authentication = SecurityContextHolder.getContext()
//                         .getAuthentication();
//        if(Objects.isNull(authentication)){
            return securityProperties.getBrowser().getLoginPage();
//        }
//        return "/resource";
    }
}