package com.time.article.security.core.captcha;

import com.alibaba.druid.util.StringUtils;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 自定义过滤器为了来检查提交的验证码 只执行一次
 * 需要放到UsernamePasswordAuthenticationFilter前面
 *
 * @author suiguozhen on 18/09/21
 */
public class ValidateCaptchaFilter extends OncePerRequestFilter {

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Setter
    private AuthenticationFailureHandler browserAuthenticationFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if ("/authentication/form".equals(request.getRequestURI()) && "post".equalsIgnoreCase(request.getMethod())) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCaptchaException e) {

//                browserAuthenticationFailureHandler.onAuthenticationFailure(request, response);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证验证码
     *
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        Captcha captcha = (Captcha) sessionStrategy.getAttribute(request, ValidateCaptchaController.CAPTCHA_KEY);
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), "captcha");
        if (Objects.isNull(captcha)) {
            throw new ValidateCaptchaException("验证码不存在");
        }
        if (captcha.isExpired()) {
            throw new ValidateCaptchaException("验证码已过期");
        }
        if (StringUtils.isEmpty(code)) {
            throw new ValidateCaptchaException("验证码不能为空");
        }
        if (!StringUtils.equalsIgnoreCase(captcha.getCode(), code)) {
            throw new ValidateCaptchaException("验证码不匹配");
        }
    }
}