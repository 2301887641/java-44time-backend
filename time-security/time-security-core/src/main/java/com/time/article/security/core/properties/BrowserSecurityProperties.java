package com.time.article.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 浏览器
 *
 * ConfigurationProperties：主要用来把properties配置文件转化为bean来使用
 * 如果只配置ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的
 * @author suiguozhen on 18/09/18
 */
@Getter
@Setter
public class BrowserSecurityProperties {
    private String loginPage="/security_login.html";
}
