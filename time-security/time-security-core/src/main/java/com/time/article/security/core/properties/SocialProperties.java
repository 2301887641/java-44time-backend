package com.time.article.security.core.properties;

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
    private QQProperties qq = new QQProperties();
}
