package com.time.article.generator.handler.dao;

import com.time.article.generator.generate.dao.CriteriaFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapper处理器
 * @author suiguozhen on 18/08/29
 */
@Component
public class CriteriaHandler extends BaseHandler {
    @Autowired
    private CriteriaFactory criteriaFactory;

    @Override
    public void generate() {
        criteriaFactory.run();
    }
}
