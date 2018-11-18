package com.time.article.security.filter;


import com.time.article.security.bean.Validate;
import com.time.article.security.constants.SecurityConstants;
import com.time.article.security.core.exception.CustomizedAuthenticationException;
import com.time.article.security.core.handler.UnificationAuthenticationFailureHandler;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

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
                return;
            }


        }
        filterChain.doFilter(request, response);
    }

    /**
     * 检查验证码
     * @param request
     */
    private void validateCaptcha(HttpServletRequest request) {
        String captcha = request.getParameter(SecurityConstants.PARAMETER_CAPTCHA);
        if (Strings.isNullOrEmpty(captcha)) {
            throw new CustomizedAuthenticationException("请传递验证码");
        }
        Validate validate = (Validate) request.getSession().getAttribute(SecurityConstants.SESSION_CAPTCHA_NAME);
        if(Objects.isNull(validate)){
            throw new CustomizedAuthenticationException("请先生成验证码");
        }
        if(!StringUtils.equals(validate.getCode(),captcha)){
            throw new CustomizedAuthenticationException("验证码不匹配");
        }
        if(LocalDateTime.now().isAfter(validate.getExpireTime())){
            throw new CustomizedAuthenticationException("验证码已过期");
        }
        request.getSession().removeAttribute(SecurityConstants.SESSION_CAPTCHA_NAME);
    }

    public UnificationAuthenticationFailureHandler getUnificationAuthenticationFailureHandler() {
        return unificationAuthenticationFailureHandler;
    }

    public void setUnificationAuthenticationFailureHandler(UnificationAuthenticationFailureHandler unificationAuthenticationFailureHandler) {
        this.unificationAuthenticationFailureHandler = unificationAuthenticationFailureHandler;
    }
}
