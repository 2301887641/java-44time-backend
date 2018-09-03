package com.time.article.generator.generate.service;

import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.base.BaseFactory;
import com.time.article.generator.service.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * service factory
 * @author suiguozhen on 18/09/03
 */
@Component
public class ServiceFactory extends BaseFactory {
    @Autowired
    private Service service;
    @Autowired
    private Entity entity;

    @Override
    public void run() {
        service.setPrimary(entity.getPrimary());
        makeFile(makeDir(service.getServiceTargetProject())+service.getServiceName()+service.getSuffix());
        build(service,service.getTemplateName());
    }
}
