package com.time.article.generator.generate.service;

import com.time.article.generator.generate.base.BaseFactory;
import com.time.article.generator.service.dto.DtoCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dtoCriteria 工厂
 * @author suiguozhen on 18/09/01
 */
@Component
public class DtoCriteriaFactory extends BaseFactory {
    @Autowired
    private DtoCriteria dtoCriteria;

    @Override
    public void run() {
        makeFile(makeDir(dtoCriteria.getDtoCriteriaTargetProject())+dtoCriteria.getDtoCriteriaName()+dtoCriteria.getSuffix());
        build(dtoCriteria,dtoCriteria.getTemplateName());
    }

}
