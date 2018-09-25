package com.time.article.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 验证码相关属性包含验证码和短信验证码
 * @author suiguozhen on 18/09/25
 */
@Getter
@Setter
public class ValidateCodeProperties {
    /**图片验证码*/
    private CaptchaProperties captcha =new CaptchaProperties();
}
