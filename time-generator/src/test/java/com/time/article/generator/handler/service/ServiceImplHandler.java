package com.time.article.generator.handler.service;

import com.time.article.generator.generate.service.ServiceImplFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/09/06
 */
@Component
public class ServiceImplHandler extends BaseHandler {
    @Autowired
    private ServiceImplFactory serviceImplFactory;

    @Override
    public void generate() {
        serviceImplFactory.run();
    }
}
