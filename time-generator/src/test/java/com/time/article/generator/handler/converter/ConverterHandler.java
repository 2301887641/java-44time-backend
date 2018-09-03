package com.time.article.generator.handler.converter;

import com.time.article.generator.generate.converter.ConverterFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * conver转换器
 * @author suiguozhen on 18/09/03
 */
@Component
public class ConverterHandler extends BaseHandler {
    @Autowired
    private ConverterFactory converterFactory;

    @Override
    public void generate() {
        converterFactory.run();
    }
}
