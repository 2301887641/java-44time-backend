package com.time.article.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码属性
 * @author suiguozhen on 18/09/25
 */
@Getter
@Setter
public class CaptchaProperties {
    /**验证码 宽度 高度 长度 过期时间*/
    private String width;
    private String height;
    private String length;
    private int expireIn;
}
