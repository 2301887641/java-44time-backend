package com.time.article.security.filter;


import com.alibaba.druid.util.StringUtils;
import com.time.article.security.constants.SecurityConstants;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * @author suiguozhen on 18/11/15
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 如果是提交表单请求的话
         */
        if(StringUtils.equals(request.getContextPath(), SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                && StringUtils.equalsIgnoreCase(request.getMethod(),SecurityConstants.DEFAULT_POST_REQUEST)){

        }
        filterChain.doFilter(request,response);
    }
}
