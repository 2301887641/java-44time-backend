package com.time.article.generator.generate.service;

import com.jayway.jsonpath.Criteria;
import com.time.article.generator.dao.criteria.EntityCriteria;
import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.base.BaseFactory;
import com.time.article.generator.service.service.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * serviceImpl 工厂
 * @author suiguozhen on 18/09/06
 */
@Component
public class ServiceImplFactory extends BaseFactory {
    @Autowired
    private ServiceImpl serviceImpl;
    @Autowired
    private Entity entity;
    @Autowired
    private EntityCriteria entityCriteria;

    @Override
    public void run() {
        serviceImpl.setPrimary(entity.getPrimary());
        makeFile(makeDir(serviceImpl.getServiceImplTargetProject())+serviceImpl.getServiceImplName()+serviceImpl.getSuffix());
        build(serviceImpl,serviceImpl.getTemplateName());
    }
}
