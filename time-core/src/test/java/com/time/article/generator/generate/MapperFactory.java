package com.time.article.generator.generate;

import com.time.article.generator.dao.base.BaseAttribute;
import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.dao.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapper工厂
 *
 * @author suiguozhen on 18/08/29
 */
@Component
public class MapperFactory extends BaseFactory {
    @Autowired
    private Mapper mapper;
    @Autowired
    private Entity entity;

    private String mapperNameSuffix = "Mapper";

    @Override
    public void run() {
        mapper.setPrimary(entity.getPrimary());
        makeFile(makeDir(mapper.getTargetProject()) + mapper.getEntityName() + mapperNameSuffix + mapper.getSuffix());
        build(mapper, mapper.getTemplateName());
    }
}
