package com.time.article.security.core.authentication.mobile;

import com.time.article.security.core.constants.SecurityConstants;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * 短信登录的过滤器
 *
 * @author suiguozhen on 18/10/03
 */
public class SmsAuthenticationFilter extends
        AbstractAuthenticationProcessingFilter {
    /**
     * 请求中携带的参数名
     */
    public static final String SMS_MOBILE_KEY = "mobile";
    /**
     * 请求中携带参数的名字是什么
     */
    private String mobileParameter = SMS_MOBILE_KEY;
    /**
     * 是否仅处理post请求
     */
    private boolean postOnly = true;

    /**
     * 处理的短信登录的请求是什么
     */
    public SmsAuthenticationFilter() {
        super(new AntPathRequestMatcher(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("不支持当前请求方式");
        }
        String mobile = obtainMobile(request);
        mobile = (Objects.isNull(mobile)) ? "" : mobile.trim();
        SmsAuthenticationToken authRequest = new SmsAuthenticationToken(mobile);
        /**将请求的信息设置在Token中*/
        setDetails(request, authRequest);
        /**拿着Token调用AuthenticationManager*/
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    /**
     * 获取请求参数中的手机号
     *
     * @param request
     * @return
     */
    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    /**
     * 将请求的信息放到Token中
     *
     * @param request
     * @param authRequest
     */
    protected void setDetails(HttpServletRequest request,
                              SmsAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    /**
     * 设置携带的参数名
     *
     * @param mobileParameter
     */
    public void setMobileParameter(String mobileParameter) {
        Assert.hasText(mobileParameter, "请输入手机号");
        this.mobileParameter = mobileParameter;
    }

    /**
     * 设置是否只允许post
     *
     * @param postOnly
     */
    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }

    /**
     * 获取携带的参数名
     *
     * @return
     */
    public final String getMobileParameter() {
        return mobileParameter;
    }
}