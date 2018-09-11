package com.time.article.generator.handler.controller;

import com.time.article.generator.generate.controller.ControllerFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * controller handler
 * @author suiguozhen on 18/09/11
 */
@Component
public class ControllerHandler extends BaseHandler {
    @Autowired
    private ControllerFactory controllerFactory;

    @Override
    public void generate() {
        controllerFactory.run();
    }
}
