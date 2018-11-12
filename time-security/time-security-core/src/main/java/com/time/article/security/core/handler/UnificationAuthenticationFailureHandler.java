package com.time.article.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.core.message.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * form表单自定义登陆错误处理
 *
 * @author suiguozhen on 18/09/21
 */
@Component
public class UnificationAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    /**
     * 手工转换json
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(objectMapper.writeValueAsString(Result.failed(exception.getMessage())));
    }
}