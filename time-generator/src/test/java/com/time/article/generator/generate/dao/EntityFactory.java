package com.time.article.generator.generate.dao;

import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.base.BaseFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/08/27
 */
@Slf4j
@Component("entityFactory")
public class EntityFactory extends BaseFactory {

    @Autowired
    private Entity entity;

    @Override
    public void run() {
        makeFile(makeDir(entity.getTargetProject()) + entity.getEntityName()  + entity.getSuffix());
        build(entity,entity.getTemplateName());
    }
}
