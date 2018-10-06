package com.time.article.security.core.social.config;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 自定义SpringSocialConfigurer
 *
 * @author suiguozhen on 18/10/06
 */
public class CustomSpringSocialConfigurer extends SpringSocialConfigurer {

    private String filterProcessesUrl;

    /**
     * 通过构造函数传递
     * @param filterProcessesUrl
     */
    public CustomSpringSocialConfigurer(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    /**
     * 重写掉
     * @param object
     * @param <T>
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter socialAuthenticationFilter = (SocialAuthenticationFilter) super.postProcess(object);
        socialAuthenticationFilter.setFilterProcessesUrl(filterProcessesUrl);
        return (T)socialAuthenticationFilter;
    }
}
