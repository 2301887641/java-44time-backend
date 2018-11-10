package com.time.article.admin.controller.business.login;

import com.time.article.admin.constants.AdminConstants;
import com.time.article.service.api.business.user.UserService;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Objects;

/**
 * 登陆控制
 *
 * @author suiguozhen on 18/10/31
 */
@Controller
public class LoginController {

    private static final String TEMPLATE_PREFIX = "/login";

    @GetMapping("/login")
    public String login() {
        return AdminConstants.TEMPLATE_PREFIX + TEMPLATE_PREFIX + "/login";
    }

}
