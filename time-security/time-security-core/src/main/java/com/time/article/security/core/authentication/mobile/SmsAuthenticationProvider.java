package com.time.article.security.core.authentication.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Objects;

/**
 * 提供校验逻辑
 * @author suiguozhen on 18/10/03
 */
@Getter
@Setter
public class SmsAuthenticationProvider implements AuthenticationProvider {
    /**需要使用它来获取用户信息*/
    private UserDetailsService userDetailsService;

    /**
     * 身份认证的逻辑
     *
     * @param authentication 其实就是我们自己定义的SmsAuthenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken smsAuthentication = (SmsAuthenticationToken) authentication;
        UserDetails userDetails = userDetailsService.loadUserByUsername((String) smsAuthentication.getPrincipal());
        if(Objects.isNull(userDetails)){
            throw new InternalAuthenticationServiceException("请用户不存在");
        }
        /**
         * 开始构造用户信息 需要使用两个参数的构造函数
         * 第一个参数:用户信息
         * 第二个参数:用户的权限
         */
        SmsAuthenticationToken smsAuthenticationToken = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        /**在过滤器里面实例化未认证的token的时候(一个参数的构造函数),已经把请求的信息设置到了token里面去了*/
        /**现在认证完成后,现在需要把之前未认证时token里面的请求信息copy到已认证的token中去*/
        smsAuthenticationToken.setDetails(smsAuthentication.getDetails());
        return smsAuthenticationToken;
    }

    /**
     * AuthenticationManager带着Token调用Provider
     * 判断传进来的Token最终调用的是哪个Provider
     * @param authentication
     * @return
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
