package com.time.article.security.core.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.time.article.security.core.exception.CustomizedAuthenticationException;
import com.time.exception.constant.ConstantPool;
import com.time.exception.enums.RestCodeEnum;
import com.time.exception.result.Result;
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
        response.setContentType(ConstantPool.RESPONSE_CONTENT_TYPE);
        Integer code = RestCodeEnum.EXCEPTION.getOrdinal();
        if (exception instanceof CustomizedAuthenticationException) {
            code = ((CustomizedAuthenticationException) exception).getCode();
        }
        response.getWriter().write(objectMapper.writeValueAsString(Result.failed(code, exception.getMessage())));
    }
}