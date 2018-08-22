package com.time.article.admin.controller.business.permission;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author suiguozhen on 18/08/22
 */
@Controller
public class IndexController {

    @RequestMapping("/login")
    public String index(){
        return "index";
    }
}
