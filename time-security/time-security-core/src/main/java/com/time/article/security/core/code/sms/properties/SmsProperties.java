package com.time.article.security.core.code.sms.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 短信属性
 * @author suiguozhen on 18/09/30
 */
@Getter
@Setter
public class SmsProperties {
    /**验证码 宽度 高度 长度 过期时间 验证url*/
    private int length=6;
    private int expireIn=60;
    private String validateUrls;
}