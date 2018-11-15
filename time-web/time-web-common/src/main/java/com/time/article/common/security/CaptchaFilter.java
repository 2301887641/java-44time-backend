package com.time.article.common.security;


import com.alibaba.druid.util.StringUtils;
import com.time.article.common.constants.SecurityConstants;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author suiguozhen on 18/11/15
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //如果是提交表单请求的话
        if(StringUtils.equals(request.getContextPath(), SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                && StringUtils.equalsIgnoreCase(request.getMethod(),SecurityConstants.DEFAULT_POST_REQUEST)){

        }
        System.out.println("once befor");
        filterChain.doFilter(request,response);
    }
}
