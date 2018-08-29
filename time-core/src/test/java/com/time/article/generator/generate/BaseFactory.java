package com.time.article.generator.generate;

import com.time.article.generator.dao.base.BaseAttribute;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 基类构建
 *
 * @author suiguozhen on 18/08/27
 */
@Slf4j
public abstract class BaseFactory {
    //最终的输出文件
    private String outputFile;

    //统一执行方法
     public abstract void run();

    /**
     * 创建目标目录
     *
     * @param targetPackage
     * @return
     */
    protected String makeDir(String targetPackage) {
        File project = new File(targetPackage);
        if (!project.exists()) {
            project.mkdirs();
        }
        //返回的绝对地址不会在最后加\,这样统一自己加上即可。
        return project.getAbsolutePath()+"\\";
    }

    /**
     * 初始化创建文件
     *
     * @param generatedFileName
     */
    protected void makeFile(String generatedFileName) {
        outputFile = generatedFileName;
        File file = new File(generatedFileName);
        if (!file.exists()) {
            try {
                log.info("正在创建文件");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 构建模板
     * @param entity
     * @param templateName
     */
    protected void build(BaseAttribute entity, String templateName) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        Template template = null;
        FileWriter fileWriter = null;
        try {
            configuration.setClassForTemplateLoading(BaseFactory.class, entity.getTemplatePath());
            configuration.setDefaultEncoding("utf-8");
            template = configuration.getTemplate(templateName);
            fileWriter = new FileWriter(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            template.process(entity, fileWriter);
        } catch (Exception e) {
            log.error("生成模板失败");
            e.printStackTrace();
        }
    }
}
