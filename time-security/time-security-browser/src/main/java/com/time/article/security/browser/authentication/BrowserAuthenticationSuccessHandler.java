package com.time.article.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.security.core.enums.LoginTypeEnum;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * form表单自定义登陆成功处理
 * @author suiguozhen on 18/09/20
 */
@Component("browserAuthenticationSuccessHandler")
public class BrowserAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        /**json处理*/
        if(LoginTypeEnum.REST.getLabel().equals(securityProperties.getBrowser().getLoginType().getLabel())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication.getPrincipal()));
            return;
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}