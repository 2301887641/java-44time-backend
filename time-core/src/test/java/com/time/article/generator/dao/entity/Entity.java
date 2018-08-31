package com.time.article.generator.dao.entity;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 实体输出类 用于循环输出数据
 * @author suiguozhen on 18/08/27
 */
@Getter
@Setter
@Component
public class Entity extends BasePojo {
    //生成的包路径
    @Value("${generator.entity.targetProject}")
    private String targetProject;

    //模板名称
    @Value("${generator.entity.templateName}")
    private String templateName;

    //主键
    private String primary;
    //将字段数据放入集合中
    private List<Column> columns;
}
