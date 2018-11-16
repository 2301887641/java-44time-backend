package com.time.article.security.filter;


import com.alibaba.druid.util.StringUtils;
import com.time.article.security.constants.SecurityConstants;
import com.time.article.security.core.exception.CustomizedAuthenticationException;
import com.time.article.security.core.handler.UnificationAuthenticationFailureHandler;
import org.assertj.core.util.Strings;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 验证码过滤器
 * @author suiguozhen on 18/11/15
 */
public class CaptchaFilter extends OncePerRequestFilter {

    private UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String contextPath = request.getRequestURI();
        /**
         * 如果是提交表单请求的话
         */
        if(StringUtils.equals(request.getRequestURI(), SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                && StringUtils.equalsIgnoreCase(request.getMethod(),SecurityConstants.DEFAULT_POST_REQUEST)){
            //检查验证码
            String captcha = ServletRequestUtils.getStringParameter(request, "captcha");
            if(Strings.isNullOrEmpty(captcha)){
                throw new CustomizedAuthenticationException("abc");
//                unificationAuthenticationFailureHandler.onAuthenticationFailure(request,response,);

            }
            String code = (String)request.getSession().getAttribute(SecurityConstants.SESSION_CAPTCHA_NAME);

        }
        filterChain.doFilter(request,response);
    }

    public UnificationAuthenticationFailureHandler getUnificationAuthenticationFailureHandler() {
        return unificationAuthenticationFailureHandler;
    }

    public void setUnificationAuthenticationFailureHandler(UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler) {
        this.unificationAuthenticationFailureHandler = unificationAuthenticationFailureHandler;
    }
}
