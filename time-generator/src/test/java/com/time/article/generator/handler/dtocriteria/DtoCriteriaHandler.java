package com.time.article.generator.handler.dtocriteria;

import com.time.article.generator.generate.dtocriteria.DtoCriteriaFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dtoCriteria 处理器
 * @author suiguozhen on 18/09/01
 */
@Component
public class DtoCriteriaHandler extends BaseHandler {
    @Autowired
    private DtoCriteriaFactory dtoCriteriaFactory;

    @Override
    public void generate() {
        dtoCriteriaFactory.run();
    }
}
