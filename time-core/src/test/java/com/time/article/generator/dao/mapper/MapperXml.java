package com.time.article.generator.dao.mapper;

import com.time.article.generator.dao.base.BaseAttribute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

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
    //xml后缀
    private String suffix=".xml";
    //Mapper后缀
    private String mapperXmlSuffix="Mapper";
    //resultMap List集合
    private List<ResultMap> resultMapList=new LinkedList<>();
    //sql字段
    private List<String> field=new LinkedList<>();
}
