package com.time.article.generator.generate.converter;

import com.time.article.generator.generate.base.BaseFactory;
import com.time.article.generator.service.converter.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * converter factory
 * @author suiguozhen on 18/09/03
 */
@Component
public class ConverterFactory extends BaseFactory {
    @Autowired
    private Converter converter;

    @Override
    public void run() {
        makeFile(makeDir(converter.getConverterTargetProject())+converter.getConverterName()+converter.getSuffix());
        build(converter,converter.getTemplateName());
    }
}
