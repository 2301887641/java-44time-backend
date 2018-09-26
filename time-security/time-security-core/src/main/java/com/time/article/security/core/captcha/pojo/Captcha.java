package com.time.article.security.core.captcha.pojo;

import lombok.Getter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @author suiguozhen on 18/09/21
 */
@Getter
public class Captcha {
    /**验证码*/
    private BufferedImage image;
    /**验证码字符串*/
    private String code;
    /**过期时间*/
    private LocalDateTime expireTime;

    /**
     * 验证码在session中存储的键名
     */
    public static final String CAPTCHA_KEY = "captcha";

    public Captcha(BufferedImage image, String code, long expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    /**是否已过期*/
    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
