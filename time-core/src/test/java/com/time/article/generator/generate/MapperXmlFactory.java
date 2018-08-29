package com.time.article.generator.generate;

import com.time.article.generator.dao.mapper.MapperXml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/08/29
 */
@Component
public class MapperXmlFactory extends BaseFactory{
    @Autowired
    private MapperXml mapperXml;

    @Override
    public void run() {
        makeFile(makeDir(mapperXml.getTargetProject())+mapperXml.getEntityName()+mapperXml.getMapperXmlSuffix()+mapperXml.getSuffix());
        build(mapperXml,mapperXml.getTemplateName());
    }
}
