package com.time.article.security.core.social.properties;

import com.time.article.security.core.social.qq.properties.QQProperties;
import com.time.article.security.core.social.wechat.properties.WeChatProperties;
import lombok.Getter;
import lombok.Setter;

/**
 * social社交
 *
 * @author suiguozhen on 18/09/29
 */
@Getter
@Setter
public class SocialProperties {
    /**
     * 默认的拦截qq登陆为/auth
     */
    private String filterProcessesUrl = "/auth";
    /**
     * qq配置
     */
    private QQProperties qq = new QQProperties();
    /**
     * 微信配置
     */
    private WeChatProperties weChat =new WeChatProperties();
}
