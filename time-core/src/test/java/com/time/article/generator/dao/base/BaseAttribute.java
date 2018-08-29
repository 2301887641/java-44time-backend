package com.time.article.generator.dao.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

/**
 * 基类属性
 * @author suiguozhen on 18/08/29
 */
@Getter
@Setter
public class BaseAttribute {
    //{{公共}} 模板路径
    @Value("${generator.templatePath}")
    private String templatePath;
    //{{公共}} 作者
    @Value("${generator.author}")
    private String author;
    //{{公共}} 时间
    private String createTime= LocalDate.now().toString();
    //{{公共}} java后缀
    private String suffix = ".java";


    //{{实体}} 名称
    @Value("${generator.entity.entityName}")
    private String entityName;
    //{{实体}} 生成的包路径
    @Value("${generator.entity.targetPackage}")
    private String entityPackage;
    //{{实体}} 基类entity的名称
    @Value("${generator.entity.baseEntityName}")
    private String baseEntityName;
    //{{实体}} 基类entity的包路径
    @Value("${generator.entity.baseEntityPackage}")
    private String baseEntityPackage;

    //{{criteria}} 包路径
    @Value("${generator.criteria.targetPackage}")
    private String criteriaTargetPackage;

    //{{mapper}} 包路径
    @Value("${generator.mapper.targetPackage}")
    private String mapperTargetPackage;
    //{{mapper}} 基类包路径
    @Value("${generator.mapper.baseMapperPackage}")
    private String baseMapperPackage;
}
