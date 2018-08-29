package com.time.article.generator.handler;

import com.time.article.generator.generate.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapper生成器
 * @author suiguozhen on 18/08/29
 */
@Component
public class MapperHandler extends BaseHandler{
    @Autowired
    private MapperFactory mapperFactory;

    @Override
    public void generate() {
        mapperFactory.run();
    }
}
