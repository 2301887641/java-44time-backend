package com.time.article.security.core.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 增强spring security自带的UserDetailsService
 * 因为我们需要使用手机号登陆
 * @author suiguozhen on 18/10/04
 */
public interface UserDetailsServiceAdapter extends UserDetailsService {
    /**
     * 根据手机号登录
     * @param mobile
     * @return
     * @throws UsernameNotFoundException
     **/
    UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException;
}