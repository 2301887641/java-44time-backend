package com.time.article.admin.controller.business.login;

import com.time.article.admin.constants.AdminConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
        return AdminConstants.BACKEND_TEMPLATE_PREFIX + TEMPLATE_PREFIX + "/login";
    }

}
