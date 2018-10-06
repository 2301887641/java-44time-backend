package com.time.article.security.core.code.api;

import com.time.article.security.core.code.sms.pojo.Sms;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 抽象验证码类 应用级配置
 *
 * @author suiguozhen on 18/09/26
 */
public abstract class AbstractCodeGenerator {

    /**
     * 生成码
     * @param request
     * @return
     */
    public abstract Sms buildCode(ServletWebRequest request);
}