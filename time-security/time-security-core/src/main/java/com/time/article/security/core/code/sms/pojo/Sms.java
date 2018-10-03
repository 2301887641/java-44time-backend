package com.time.article.security.core.code.sms.pojo;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 短信pojo
 * @author suiguozhen on 18/09/29
 */
@Getter
public class Sms {
    /**验证码字符串*/
    private String code;
    /**过期时间*/
    private LocalDateTime expireTime;

    public Sms(String code, long expireTime) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    /**是否已过期*/
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
