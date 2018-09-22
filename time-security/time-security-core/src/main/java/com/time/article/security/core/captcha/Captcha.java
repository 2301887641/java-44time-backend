package com.time.article.security.core.captcha;

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

    public Captcha(BufferedImage image, String code, long expireTime) {
        this.image = image;
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireTime);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expireTime);
    }
}
