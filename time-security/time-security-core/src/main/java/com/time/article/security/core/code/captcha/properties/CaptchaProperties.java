package com.time.article.security.core.code.captcha.properties;

import com.time.article.security.core.code.sms.properties.SmsProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * 验证码属性
 *
 * @author suiguozhen on 18/09/25
 */
@Getter
@Setter
public class CaptchaProperties extends SmsProperties {
    //设置验证码为4位
    public CaptchaProperties(){
        setLength(4);
    }
    private int width = 110;
    private int height = 40;
}