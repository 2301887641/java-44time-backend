package com.time.article.security.core.defaults;

import com.time.article.security.core.code.api.SmsSender;

/**
 * 默认验证码发送器
 * @author suiguozhen on 18/09/29
 */
public class DefaultSmsSender implements SmsSender {
    /**
     * 发送短信验证码
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    @Override
    public void send(String mobile, String code) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("向手机").append(mobile).append("发送验证码").append(code);
        System.out.println(stringBuilder);
    }
}
