package com.time.article.security.core.properties;

import com.time.article.security.core.constants.SecurityConstants;
import com.time.article.security.core.enums.LoginTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 浏览器
 * <p>
 * ConfigurationProperties：主要用来把properties配置文件转化为bean来使用
 * 如果只配置ConfigurationProperties注解，在IOC容器中是获取不到properties配置文件转化的bean的
 *
 * @author suiguozhen on 18/09/18
 */
@Getter
@Setter
public class BrowserSecurityProperties {
    /**
     * 自定义登录页
     */
    private String loginPage = SecurityConstants.DEFAULT_LOGIN_PAGE_URL;
    /**
     * 自定义登陆方式
     */
    private LoginTypeEnum loginType = LoginTypeEnum.REST;
    /**remember记住时间 秒*/
    private int rememberMeSeconds = 3600;
}