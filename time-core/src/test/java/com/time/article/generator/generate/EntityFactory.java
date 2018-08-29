package com.time.article.generator.generate;

import com.time.article.generator.dao.criteria.Criteria;
import com.time.article.generator.dao.entity.Entity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author suiguozhen on 18/08/27
 */
@Slf4j
@Component("entityFactory")
public class EntityFactory extends BaseFactory{

    @Autowired
    private Entity entity;

    @Override
    public void run() {
        makeFile(makeDir(entity.getTargetProject()) + "\\" + entity.getEntityName()  + entity.getSuffix());
        build(entity,entity.getTemplateName());
    }
}
