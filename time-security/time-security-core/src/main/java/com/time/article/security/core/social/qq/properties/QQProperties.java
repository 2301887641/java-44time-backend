package com.time.article.security.core.social.qq.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ用户
 *
 * @author suiguozhen on 18/09/29
 */
@Getter
@Setter
public class QQProperties extends SocialProperties {
    /**服务提供商的标识*/
    private String providerId = "qq";
}
