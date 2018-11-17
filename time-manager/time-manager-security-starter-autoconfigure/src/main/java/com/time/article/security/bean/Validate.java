package com.time.article.security.bean;

import java.time.LocalDateTime;

/**
 * 验证码相关bean文件
 * @author suiguozhen on 18/11/17
 */
public class Validate {
    /**
     * 验证码
     */
    private String code;
    /**
     * 过期时间 当前时间加上秒数
     */
    private LocalDateTime expireTime;

    public Validate(String code, Long time) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(time);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }
}