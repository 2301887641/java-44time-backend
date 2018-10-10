package com.time.article.rest.controller.business.login;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

/**
 * 实现第三方自动注册 不用填写注册信息
 * @author suiguozhen on 18/10/09
 */
@Component
public class UnificationConnectionSignUp implements ConnectionSignUp {

    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return "admin";
    }
}
