package com.time.article.security.core.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码相关处理器
 * @author suiguozhen on 18/09/30
 */
public interface CodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 创建验证码的整个过程
     * @param request
     */
    void create(ServletWebRequest request) throws Exception;
}
