package com.time.article.admin.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * admin模块定制的UserDetail对象
 * 根据自己的需求将一些常用的用户信息封装到 UserDetails 中，便于快速获取用户信息，比如用户ID、昵称等。
 * @author suiguozhen on 18/11/12
 */
@Getter
@Setter
public class AdminUserDetails extends User {

    private Integer id;
    private String avatar;

    /**
     * 构造函数 需比User多传递我们需要的参数
     *
     * @param id          userId
     * @param username    用户名
     * @param password    密码
     * @param avatar      头像
     * @param authorities 权限
     */
    public AdminUserDetails(Integer id, String username, String password, String avatar, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.avatar = avatar;
    }

}
