package com.time.article.security.browser.controller;

import com.time.article.security.core.constants.SecurityConstants;
import com.time.article.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一登陆跳转 控制器
 *
 * @author suiguozhen on 18/09/17
 */
//@Controller
@Slf4j
public class BrowserSecurityController {

    @Autowired
    private SecurityProperties securityProperties;



    /**
     * 登陆页面转发 get请求重定向跳转 rest返回401请求
     *
     * @return
     */
    @RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
    public String loginPage(HttpServletRequest request, HttpServletResponse response) {
        //TODO 这里需要检查cookie然后跳转到首页上去index
//        Authentication authentication = SecurityContextHolder.getContext()
//                         .getAuthentication();
//        if(Objects.isNull(authentication)){
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:" + targetUrl);
        }

            return securityProperties.getBrowser().getLoginPage();
//        }
//        return "/resource";
    }
}