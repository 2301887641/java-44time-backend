package com.time.article.security.browser;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 安全属性 包含其他几种:BrowserSecurityProperties
 * EnableConfigurationProperties: 将当前配置文件转换成容器中的bean,使@ConfigurationProperties注解生效
 *
 * @author suiguozhen on 18/09/18
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "custom.security")
public class SecurityProperties {
    private BrowserSecurityProperties browser = new BrowserSecurityProperties();
}
