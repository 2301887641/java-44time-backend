package com.time.article.generator.generate;

import com.time.article.generator.dao.entity.Entity;

/**
 * 基类构建
 * @author suiguozhen on 18/08/27
 */
public abstract class BaseGenerate {
    //模板路径
    private String basePackagePath;
    private String templateName;

    abstract public void create(Entity entity);
    abstract public void checkDir();
}
