package com.time.article.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.exception.constant.ConstantPool;
import com.time.exception.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义成功处理
 * @author suiguozhen on 18/11/12
 */
@Component
public class UnificationAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType(ConstantPool.RESPONSE_CONTENT_TYPE);
        response.getWriter().write(objectMapper.writeValueAsString(Result.success(authentication.getPrincipal())));
    }
}