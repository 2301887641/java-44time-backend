package com.time.article.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author suiguozhen on 18/06/28
 */
@Controller
public class UserController {
//    @Autowired
//    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public ResponseEntity test(){
        return null;
//        userService.add();
//        return ResponseEntity.ok(userService.add());
    }
}
