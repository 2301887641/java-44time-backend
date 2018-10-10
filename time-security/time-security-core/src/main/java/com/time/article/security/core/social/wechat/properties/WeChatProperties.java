package com.time.article.security.core.social.wechat.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信属性配置
 * @author suiguozhen on 18/10/10
 */
@Getter
@Setter
public class WeChatProperties extends SocialProperties {
    /**
     * 第三方id，用来决定发起第三方登录的url，默认是 weixin。
     */
    private String providerId = "weChat";

}
