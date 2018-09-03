package com.time.article.generator.handler.dto;

import com.time.article.generator.generate.dto.DtoFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dto 处理器
 * @author suiguozhen on 18/08/31
 */
@Component
public class DtoHandler extends BaseHandler {
    @Autowired
    private DtoFactory dtoFactory;

    @Override
    public void generate() {
        dtoFactory.run();
    }
}
