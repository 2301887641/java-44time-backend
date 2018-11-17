package com.time.article.security.filter;


import com.alibaba.druid.util.StringUtils;
import com.time.article.security.bean.Validate;
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

/**
 * 验证码过滤器
 *
 * @author suiguozhen on 18/11/15
 */
public class CaptchaFilter extends OncePerRequestFilter {

    private UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 如果是提交表单请求的话
         */
        if (StringUtils.equals(request.getRequestURI(), SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                && StringUtils.equalsIgnoreCase(request.getMethod(), SecurityConstants.DEFAULT_POST_REQUEST)) {
            try {
                validateCaptcha(request);
            } catch (CustomizedAuthenticationException e) {
                /**
                 * 这里只能这样抛出异常 其他异常不行
                 */
                unificationAuthenticationFailureHandler.onAuthenticationFailure(request, response, e);
            }
            Validate validate = (Validate) request.getSession().getAttribute(SecurityConstants.SESSION_CAPTCHA_NAME);


        }
        filterChain.doFilter(request, response);
    }

    private void validateCaptcha(HttpServletRequest request) {
        //检查验证码
        String captcha = request.getParameter("captcha");
        if (Strings.isNullOrEmpty(captcha)) {
            throw new CustomizedAuthenticationException("abc");
        }
    }

    public UnificationAuthenticationFailureHandler getUnificationAuthenticationFailureHandler() {
        return unificationAuthenticationFailureHandler;
    }

    public void setUnificationAuthenticationFailureHandler(UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler) {
        this.unificationAuthenticationFailureHandler = unificationAuthenticationFailureHandler;
    }
}
