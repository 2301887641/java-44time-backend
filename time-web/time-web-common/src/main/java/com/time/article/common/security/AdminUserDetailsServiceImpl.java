package com.time.article.common.security;

import com.time.article.common.constants.SecurityConstants;
import com.time.article.service.api.business.user.UserService;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 自定义用户验证
 * @author suiguozhen on 18/11/12
 */
@Component("UnificationUserDetailService")
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.selectPasswordByName(username);
        if(Objects.isNull(userDto)){
            throw new UsernameNotFoundException(SecurityConstants.USERNAME_OR_PASSWORD_MISSED);
        }
        return new AdminUserDetails(
                userDto.getId(),
                username,
                userDto.getPassword(),
                userDto.getAvatar(),
                AuthorityUtils.NO_AUTHORITIES
                );
    }
}
