package com.time.article.generator.generate;

/**
 * 基类构建
 * @author suiguozhen on 18/08/27
 */
public abstract class BaseGenerate {
    //模板路径
    private String basePackagePath;
    private String templateName;

    abstract public void create();
}
