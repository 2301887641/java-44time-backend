package com.time.article.rest.controller.business.login;

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
 * 自定义用户认证 处理用户表单登陆
 * 需要用户自己去实现
 *
 * @author suiguozhen on 18/09/12
 */
@Component("browserUserDetailService")
public class BrowserUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto userDto = userService.selectPasswordByName(username);
        if (Objects.isNull(userDto)) {
            /**此异常会被或略只是调用不做信息处理*/
            throw new UsernameNotFoundException("用户名不存在");
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