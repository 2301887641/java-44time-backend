package com.time.article.security.browser.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 自定义用户认证
 * 需要用户自己去实现
 *
 * @author suiguozhen on 18/09/12
 */
public class BrowserUserDetailServiceImpl implements UserDetailsService, SocialUserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return  buildUser(username);
    }

    /**
     * 社交登陆 根据userId构建UserDetails
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
        return  buildUser(userId);
    }

    /**
     * 构建用户
     * @param username
     * @return
     */
    private SocialUserDetails buildUser(String username){
        return new SocialUser(username,
                passwordEncoder.encode("123456"),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }
}