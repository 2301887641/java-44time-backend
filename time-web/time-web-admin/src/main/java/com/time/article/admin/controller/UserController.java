package com.time.article.admin.controller;

import com.time.article.service.api.business.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileOutputStream;

/**
 * @author suiguozhen on 18/06/28
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public ResponseEntity test(){
        return ResponseEntity.ok(userService.add());
    }
}
