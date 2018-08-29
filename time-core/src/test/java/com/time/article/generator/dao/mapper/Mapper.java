package com.time.article.generator.dao.mapper;

import com.time.article.generator.dao.base.BaseAttribute;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * mapper
 * @author suiguozhen on 18/08/29
 */
@Getter
@Setter
@Component
public class Mapper extends BaseAttribute {
    @Value("${generator.mapper.baseMapperName}")
    private String baseMapperName;

    @Value("${generator.mapper.targetProject}")
    private String targetProject;

    @Value("${generator.mapper.templateName}")
    private String templateName;
    //主键
    private String primary;
}
