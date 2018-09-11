package com.time.article.generator.generate.controller;

import com.time.article.generator.controller.Controller;
import com.time.article.generator.generate.base.BaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * controller factory
 * @author suiguozhen on 18/09/11
 */
@Component
public class ControllerFactory extends BaseFactory {
    @Autowired
    private Controller controller;

    @Override
    public void run() {
        makeFile(makeDir(controller.getControllerTargetProject())+controller.getControllerName()+controller.getSuffix());
        build(controller,controller.getTemplateName());
    }
}
