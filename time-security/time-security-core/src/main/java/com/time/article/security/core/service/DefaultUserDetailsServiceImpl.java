package com.time.article.security.core.service;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 默认用户认证实现 需要自己覆盖才可以
 *
 * @author suiguozhen on 18/11/12
 */
public class DefaultUserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "123456",
                true,
                true,
                true,
                true, AuthorityUtils.NO_AUTHORITIES);
    }
}
