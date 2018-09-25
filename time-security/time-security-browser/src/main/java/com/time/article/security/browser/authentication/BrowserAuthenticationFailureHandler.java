package com.time.article.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        /**json处理*/
        if(LoginTypeEnum.REST.getLabel().equals(securityProperties.getBrowser().getLoginType().getLabel())){
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(objectMapper.writeValueAsString(exception.getMessage()));
            return;
        }
        /**这里将错误信息放到session 并转发回去*/
        request.getSession().setAttribute("error",exception.getMessage());
        request.getRequestDispatcher("/authentication/loginPage").forward(request,response);
    }
}