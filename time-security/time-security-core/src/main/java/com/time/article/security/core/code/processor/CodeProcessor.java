package com.time.article.security.core.code.processor;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码相关处理器
 * @author suiguozhen on 18/09/30
 */
public interface CodeProcessor {
    void create(ServletWebRequest request);
}
