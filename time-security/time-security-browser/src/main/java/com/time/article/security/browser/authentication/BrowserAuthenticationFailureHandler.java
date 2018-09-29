package com.time.article.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.core.message.result.Result;
import com.time.article.security.core.enums.LoginTypeEnum;
import com.time.article.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * form表单自定义登陆错误处理
 * @author suiguozhen on 18/09/21
 */
@Component("browserAuthenticationFailureHandler")
public class BrowserAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /**手工转换json*/
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        /**rest处理*/
        if(LoginTypeEnum.REST.getLabel().equals(securityProperties.getBrowser().getLoginType().getLabel())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(Result.failed(exception.getMessage())));
            return;
        }
        /**兼容form表单提示 不建议使用*/
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(exception.getMessage());
    }
}