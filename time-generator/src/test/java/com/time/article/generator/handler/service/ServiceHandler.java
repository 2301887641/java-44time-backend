package com.time.article.generator.handler.service;

import com.time.article.generator.generate.service.ServiceFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * service handler
 * @author suiguozhen on 18/09/03
 */
@Component
public class ServiceHandler extends BaseHandler {
    @Autowired
    private ServiceFactory serviceFactory;

    @Override
    public void generate() {
        serviceFactory.run();
    }
}
