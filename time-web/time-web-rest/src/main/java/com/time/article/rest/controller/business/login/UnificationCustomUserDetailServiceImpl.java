package com.time.article.rest.controller.business.login;

import com.time.article.rest.constants.RestConstants;
import com.time.article.security.core.code.api.CustomUserDetailsService;
import com.time.article.service.api.business.user.UserService;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 全局统一用户认证 处理用户表单、手机号、第三方登陆
 *
 * @author suiguozhen on 18/09/12
 */
@Component("unificationUserDetailService")
public class UnificationCustomUserDetailServiceImpl implements CustomUserDetailsService, SocialUserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 根据用户名或手机号登陆
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) {
        return loadUser(username);
    }

    /**
     * 根据手机号发送验证码登录
     *
     * @param mobile
     * @return
     */
    @Override
    public UserDetails loadUserByMobile(String mobile) throws UsernameNotFoundException {
        //TODO 这里在发送验证码后 需要比对手机号等
        UserDto userDto = userService.selectPasswordByMobile(mobile);
        if (Objects.isNull(userDto)) {
            /**只能调用UsernameNotFoundException异常*/
            throw new UsernameNotFoundException(RestConstants.MOBILE_NOT_EXISTS);
        }
        return new User(userDto.getUsername(),
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.NO_AUTHORITIES
        );
    }

    /**
     * 社交登陆需要根据用户名进行查询账号
     *
     * @param userId
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public SocialUserDetails loadUserByUserId(String userId) {
        return (SocialUserDetails) loadUser(userId);
    }

    /**
     * 公共方法 获取用户信息
     *
     * @param userInfo
     * @return
     * @throws UsernameNotFoundException
     */
    private SocialUserDetails loadUser(String userInfo) throws UsernameNotFoundException {
        UserDto userDto = userService.selectPasswordByName(userInfo);
        if (Objects.isNull(userDto)) {
            /**此异常会被或略只是调用不做信息处理*/
            throw new UsernameNotFoundException(RestConstants.USER_NOT_EXISTS);
        }
        return new SocialUser(userInfo,
                userDto.getPassword(),
                true,
                true,
                true,
                true,
                AuthorityUtils.NO_AUTHORITIES
        );
    }
}