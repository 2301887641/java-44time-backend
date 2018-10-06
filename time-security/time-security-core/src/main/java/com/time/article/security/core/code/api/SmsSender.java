package com.time.article.security.core.code.api;

/**
 * 短信验证码发送接口
 * @author suiguozhen on 18/09/29
 */
public interface SmsSender {
    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @param code  验证码
     */
    void send(String mobile,String code);
}
