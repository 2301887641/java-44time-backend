package com.time.article.generator.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 实体输出类 用于循环输出数据
 * @author suiguozhen on 18/08/27
 */
@Getter
@Setter
public class Entity {
    private String packageName;
    private String baseEntityPackage;
    private String author;
    private String createTime;
    private String entityName;
    private String primary;
    //将字段数据放入集合中
    private List<Column> columns;
}
