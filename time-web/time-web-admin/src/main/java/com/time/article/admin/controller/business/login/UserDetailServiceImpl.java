package com.time.article.admin.controller.business.login;

import com.time.article.service.api.business.user.UserService;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 用户自定义身份认证
 * @author suiguozhen on 18/11/10
 */
//@Component("unificationUserDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.selectPasswordByName(username);
        if (Objects.isNull(userDto)) {
            /**此异常会被或略只是调用不做信息处理*/
            throw new UsernameNotFoundException("asdfs");
        }
        return new User(username,
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.NO_AUTHORITIES
        );
    }
}
