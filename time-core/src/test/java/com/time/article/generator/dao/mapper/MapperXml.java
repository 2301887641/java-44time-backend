package com.time.article.generator.dao.mapper;

import com.time.article.generator.dao.base.BaseAttribute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/08/29
 */
@Getter
@Setter
@Component
public class MapperXml extends BaseAttribute {
    @Value("${generator.mapperXml.targetProject}")
    private String targetProject;
    @Value("${generator.mapperXml.templateName}")
    private String templateName;

    private String suffix=".xml";

    private String mapperXmlSuffix="Mapper";
}
