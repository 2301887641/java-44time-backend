package com.time.article.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author suiguozhen on 18/07/04
 */
@ConfigurationProperties(
        prefix = "shiro"
)
public class SecurityProperties {
    private String salt;
    private String prefixUrl;
    private String casLoginUrl;
    private String callbackUrl;

    public SecurityProperties() {
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPrefixUrl() {
        return prefixUrl;
    }

    public void setPrefixUrl(String prefixUrl) {
        this.prefixUrl = prefixUrl;
    }

    public String getCasLoginUrl() {
        return casLoginUrl;
    }

    public void setCasLoginUrl(String casLoginUrl) {
        this.casLoginUrl = casLoginUrl;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
