package com.time.article.security.core.defaults;

import com.time.article.security.core.code.api.UserDetailsServiceAdapter;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 默认用户认证
 * 需要用户自己去实现
 *
 * @author suiguozhen on 18/09/12
 */
public class DefaultUserDetailServiceAdapterImpl implements UserDetailsServiceAdapter, SocialUserDetailsService {

    @Getter
    @Setter
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username){
        return  buildUser(username);
    }

    /**
     * 根据手机号登录
     *
     * @param mobile
     * @return
     */
    @Override
    public UserDetails loadUserByMobile(String mobile) {
        return  buildUser(mobile);
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
                getPasswordEncoder().encode("123456"),
                true,
                true,
                true,
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }
}