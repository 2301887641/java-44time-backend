package com.time.article.security.properties;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 验证码相关属性
 * @author suiguozhen on 18/11/16
 */
public class ValidateProperties {
    private LocalDateTime expireTime;
}
