package com.time.article.generator.handler.dao;

import com.time.article.generator.generate.dao.MapperXmlFactory;
import com.time.article.generator.handler.base.BaseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapperXml处理器
 * @author suiguozhen on 18/08/29
 */
@Component
public class MapperXmlHandler extends BaseHandler {
    @Autowired
    private MapperXmlFactory mapperXmlFactory;

    @Override
    public void generate() {
        mapperXmlFactory.run();
    }
}
