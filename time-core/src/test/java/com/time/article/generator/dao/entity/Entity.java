package com.time.article.generator.dao.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/**
 * 实体输出类 用于循环输出数据
 * @author suiguozhen on 18/08/27
 */
@Getter
@Setter
@Component
public class Entity {
    //生成的包路径
    @Value("${generator.entity.targetPackage}")
    private String packageName;
    //BaseEntity 的路径名
    @Value("${generator.entity.BaseEntityPackage}")
    private String baseEntityPackage;
    //作者
    @Value("${generator.entity.author}")
    private String author;
    @Value("${generator.entity.entityName}")
    private String entityName;
    //设置时间
    private String createTime= LocalDate.now().toString();
    private String primary;
    //将字段数据放入集合中
    private List<Column> columns;
}
