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
public class BasePojo {
    //{{公共}} 模板路径
    @Value("${generator.templatePath}")
    private String templatePath;
    //{{公共}} 表名
    @Value("${generator.entity.tableName}")
    private String tableName;
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

    //{{dto}} 包路径
    @Value("${generator.dto.targetPackage}")
    private String dtoTargetPackage;
    //{{dto}} 基类包路径
    @Value("${generator.dto.baseDtoPackage}")
    private String baseDtoPackage;
    //{{dto}} 基类名称
    @Value("${generator.dto.baseDtoName}")
    private String baseDtoName;
    //{{dto}} dto名称
    @Value("${generator.dto.dtoName}")
    private String dtoName;

    //{{dtoCriteria}} 生成目录
    @Value("${generator.dtoCriteria.targetProject}")
    private String dtoCriteriaTargetProject;
    //{{dtoCriteria}} 包路径
    @Value("${generator.dtoCriteria.targetPackage}")
    private String dtoCriteriaTargetPackage;
    //{{dtoCriteria}} 名称
    @Value("${generator.dtoCriteria.dtoCriteriaName}")
    private String dtoCriteriaName;

    //{{converter}} 生成目录
    @Value("${generator.converter.targetProject}")
    private String converterTargetProject;
    //{{converter}} 生成的包路径
    @Value("${generator.converter.targetPackage}")
    private String converterTargetPackage;
    //{{converter}} baseConverterName
    @Value("${generator.converter.baseConverterName}")
    private String baseConverterName;
    //{{converter}} baseConverterPackage
    @Value("${generator.converter.baseConverterPackage}")
    private String baseConverterPackage;
    //{{converter}} converter名称
    @Value("${generator.converter.converterName}")
    private String converterName;

    //{{service}} 生成目录
    @Value("${generator.service.targetProject}")
    private String serviceTargetProject;
    //{{service}} 包路径
    @Value("${generator.service.targetPackage}")
    private String serviceTargetPackage;
    //{{service}} 基类包路径
    @Value("${generator.service.baseServicePackage}")
    private String baseServicePackage;
    //{{service}} 基类包名
    @Value("${generator.service.baseServiceName}")
    private String baseServiceName;
    //{{service}} service名称
    @Value("${generator.service.serviceName}")
    private String serviceName;

    //{{serviceimpl}生成目录
    @Value("${generator.serviceImpl.targetProject}")
    private String serviceImplTargetProject;
    //{{serviceImpl}} 包路径
    @Value("${generator.serviceImpl.targetPackage}")
    private String serviceImplTargetPackage;
    //{{serviceImpl}} 基类包路径
    @Value("${generator.serviceImpl.baseServiceImplPackage}")
    private String baseServiceImplPackage;
    //{{serviceImpl}} 基础包名
    @Value("${generator.serviceImpl.baseServiceImplName}")
    private String baseServiceImplName;
    //{{serviceImpl}} serviceImpl名称
    @Value("${generator.serviceImpl.serviceImplName}")
    private String serviceImplName;
    //{{serviceImpl}} 继承接口名称
    @Value("${generator.serviceImpl.implementationInterfaceName}")
    private String implementationInterfaceName;
    //{{serviceImpl}} 继承接口包路径
    @Value("${generator.serviceImpl.implementationInterfacePackage}")
    private String implementationInterfacePackage;

    //{{controller}} 生成目录
    @Value("${generator.controller.targetProject}")
    private String controllerTargetProject;
    //{{controller}} 包路径
    @Value("${generator.controller.targetPackage}")
    private String controllerTargetPackage;
    //{{controller}} 控制器注释
    @Value("${generator.controller.controllerComment}")
    private String controllerComment;
    //{{controller}} 控制器路由
    @Value("${generator.controller.routerName}")
    private String controllerRouterName;
    //{{controller}} 控制器名称
    @Value("${generator.controller.controllerName}")
    private String controllerName;
}
