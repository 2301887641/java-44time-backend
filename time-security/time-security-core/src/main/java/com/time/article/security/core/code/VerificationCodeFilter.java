package com.time.article.security.core.code;

import com.time.article.security.core.exception.VerificationCodeException;
import com.time.article.security.core.code.processor.VerificationCodeProcessorDispatch;
import com.time.article.security.core.constants.SecurityConstants;
import com.time.article.security.core.enums.VerificationCodeTypeEnum;
import com.time.article.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * 统一验证码验证过滤器
 * OncePerRequestFilter，他能确保在一次请求只能通过一次filter,而不需要重复执行。
 *
 * @author suiguozhen on 18/10/05
 */
//@Component
public class VerificationCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private VerificationCodeProcessorDispatch verificationCodeProcessorDispatch;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 存放图片验证码、短信验证码需要校验的url
     **/
    private Map<String, VerificationCodeTypeEnum> urlMap = new HashMap<>(16);

    /**
     * 验证请求url与配置的url是否匹配的工具类
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        /**
         * 图片验证码默认登陆校验url
         */
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM, VerificationCodeTypeEnum.CAPTCHA);
        /**
         * 再放入自定义需要校验的图片验证码url
         */
        addUrlToMap(securityProperties.getCode().getCaptcha().getValidateUrls(), VerificationCodeTypeEnum.CAPTCHA);
        /**
         * 手机验证码登录校验url
         */
        urlMap.put(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE, VerificationCodeTypeEnum.SMS);
        /**
         * 自定义需要校验的短信验证码url
         */
        addUrlToMap(securityProperties.getCode().getSms().getValidateUrls(), VerificationCodeTypeEnum.SMS);
    }

    /**
     * 讲系统中配置的需要校验验证码的URL根据校验的类型放入map
     *
     * @param urlString
     * @param type
     */
    protected void addUrlToMap(String urlString, VerificationCodeTypeEnum type) {
        if (StringUtils.isNotBlank(urlString)) {
            /*
             * splitByWholeSeparatorPreserveAllToKens方法作用：
             * 分割字符串过程中会按照每个分隔符进行分割，不忽略任何空白项
             * */
            String[] urls = StringUtils.splitByWholeSeparatorPreserveAllTokens(urlString, ",");
            for (String url : urls) {
                urlMap.put(url, type);
            }
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        VerificationCodeTypeEnum codeType = getCodeType(request);
        if (!Objects.isNull(codeType)) {
            try {
                verificationCodeProcessorDispatch.findValidateCodeProcessor(codeType.getParamNameOnValidate()).validate(new ServletWebRequest(request, response));
            } catch (VerificationCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 获取校验码的类型，如果当前请求不需要校验，则返回null
     *
     * @param request
     * @return
     */
    private VerificationCodeTypeEnum getCodeType(HttpServletRequest request) {
        String type = "post";
        /**
         * 验证码只能发送post类型
         */
        VerificationCodeTypeEnum verificationCodeTypeEnum = null;
        if (StringUtils.equalsIgnoreCase(request.getMethod(), type)) {
            Set<String> sets = urlMap.keySet();
            for (String url : sets) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    verificationCodeTypeEnum = urlMap.get(url);
                }
            }
        }
        return verificationCodeTypeEnum;
    }
}
