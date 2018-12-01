package com.time.article.admin.controller.business.index;

import com.time.article.admin.constants.AdminConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 首页
 *
 * @author suiguozhen on 18/11/27
 */
@Controller
public class IndexController {
    private static final String TEMPLATE_PREFIX = "/index";

    @GetMapping("/index")
    public String index() {
        return AdminConstants.TEMPLATE_PREFIX + TEMPLATE_PREFIX+"/index";
    }
}
