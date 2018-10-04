package com.time.article.dao.entity.business.user;

import com.time.article.core.dao.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

/**
 *  用户相关
 * @author suiguozhen on 2018-09-27
 */
@Getter
@Setter
public class User extends BaseEntity<Integer> {

    /**
    *  手机号
    */
    private String mobile;
    /**
    *  账号
    */
    private String username;
    /**
    *  邮箱
    */
    private String email;
    /**
    *  密码
    */
    private String password;
    /**
    *  密码盐
    */
    private String salt;
    /**
    *  头像
    */
    private String avatar;
    /**
    *  是否超级管理员 1超管
    */
    private Integer administrator;
    /**
    *  账号状态 0正常
    */
    private Integer status;
    /**
    *  登陆ip
    */
    private String registerIp;
}