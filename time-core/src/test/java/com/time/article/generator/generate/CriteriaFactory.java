package com.time.article.generator.generate;

import com.time.article.generator.dao.criteria.Criteria;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * criteria工厂
 * @author suiguozhen on 18/08/29
 */
@Slf4j
@Component("criteriaFactory")
public class CriteriaFactory extends BaseFactory {
    //entity + Criteria = entityCriteria
    private String entityNameSuffix = "Criteria";

    @Autowired
    private Criteria criteria;

    @Override
    public  void run() {
        makeFile(makeDir(criteria.getTargetProject()) + "\\" + criteria.getEntityName() + entityNameSuffix + criteria.getSuffix());
        build(criteria,criteria.getTemplateName());
    }
}
