package com.time.article.security.core.code.captcha.pojo;

import com.time.article.security.core.code.sms.pojo.Sms;
import lombok.Getter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * 验证码
 *
 * @author suiguozhen on 18/09/21
 */
@Getter
public class Captcha extends Sms {
    /**验证码*/
    private BufferedImage image;

    public Captcha(BufferedImage image, String code, long expireTime) {
        super(code,expireTime);
        this.image = image;
    }
}
