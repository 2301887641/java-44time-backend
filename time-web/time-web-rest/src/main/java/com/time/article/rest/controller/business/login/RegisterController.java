package com.time.article.rest.controller.business.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 注册控制器
 *
 * @author suiguozhen on 18/09/27
 */
@Controller
public class RegisterController {

    @GetMapping("/error")
    public String error() {
        return "/error";
    }
}
