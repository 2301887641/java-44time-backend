package com.time.article.security.core.authentication.mobile;

import com.time.article.security.core.code.captcha.handler.CodeException;
import com.time.article.security.core.code.processor.CodeProcessor;
import com.time.article.security.core.code.sms.pojo.Sms;
import com.time.article.security.core.constants.SecurityConstants;
import com.time.article.security.core.enums.CodeTypeEnum;
import com.time.article.security.core.properties.SecurityProperties;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 自定义过滤器为了来检查提交的手机验证码 只执行一次
 * 需要放到UsernamePasswordAuthenticationFilter前面
 * ##此过滤器的执行非常靠前,有可能在容器启动前 引入注入的bean都失效了
 * //TODO 这里在filter里面使用InitializingBean可能会有问题 后续再想办法吧
 *
 * @author suiguozhen on 18/09/21
 */
public class SmsFilter extends OncePerRequestFilter implements InitializingBean {
    /**
     * 缓存
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Setter
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Setter
    private SecurityProperties securityProperties;
    /**
     * set集合
     **/
    private Set<String> urlSet = new HashSet<>(16);
    /**
     * 分隔符
     */
    private static final String SEPARATOR = ",";
    /**
     * 路径匹配
     */
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        /*
         * splitByWholeSeparatorPreserveAllToKens方法作用：
         * 分割字符串过程中会按照每个分隔符进行分割，不忽略任何空白项
         * */
        String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getSms().getValidateUrls(), SEPARATOR);
        if(!Objects.isNull(strings)){
            urlSet.addAll(Arrays.asList(strings));
        }
        urlSet.add(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**循环所有url**/
        for (String url : urlSet) {
            /**匹配当前url**/
            if (antPathMatcher.match(url, request.getRequestURI())) {
                try {
                    validate(new ServletWebRequest(request));
                    break;
                } catch (CodeException e) {
                    authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                    return;
                }
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
        Sms sms = (Sms) sessionStrategy.getAttribute(request, CodeProcessor.SESSION_KEY_PREFIX + "SMS");
        String code = ServletRequestUtils.getStringParameter(request.getRequest(), CodeTypeEnum.SMS.getParamNameOnValidate());
        if (Objects.isNull(sms)) {
            throw new CodeException("验证码已过期");
        }
        if (sms.isExpired()) {
            sessionStrategy.removeAttribute(request, CodeProcessor.SESSION_KEY_PREFIX + "SMS");
            throw new CodeException("验证码已过期");
        }
        if (StringUtils.isEmpty(code)) {
            throw new CodeException("验证码的值不能为空");
        }
        if (!StringUtils.equalsIgnoreCase(sms.getCode(), code)) {
            throw new CodeException("验证码不匹配");
        }
        sessionStrategy.removeAttribute(request, CodeProcessor.SESSION_KEY_PREFIX + "SMS");
    }
}