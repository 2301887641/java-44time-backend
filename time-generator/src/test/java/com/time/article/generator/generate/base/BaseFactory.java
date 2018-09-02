package com.time.article.generator.generate.base;

import com.time.article.generator.dao.base.BasePojo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

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
    //是否允许覆盖模板
    @Value("${generator.coverage}")
    private Boolean coverage;
    //模板是否存在
    private Boolean existence=false;

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
                log.info("--------------正在创建文件------------------");
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            existence=true;
        }
    }

    /**
     * 构建模板
     * @param entity
     * @param templateName
     */
    protected void build(BasePojo entity, String templateName) {
        //如果不允许覆盖的话
        if(coverage && existence){
            System.out.println(templateName+"-------------------模板已存在,不允许覆盖------------------");
            return;
        }
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        Template template;
        FileWriter fileWriter;
        try {
            configuration.setClassForTemplateLoading(BaseFactory.class, entity.getTemplatePath());
            configuration.setDefaultEncoding("utf-8");
            template = configuration.getTemplate(templateName);
            fileWriter = new FileWriter(outputFile);
            template.process(entity, fileWriter);
        } catch (Exception e) {
            log.error("--------------------------生成模板失败----------------------");
            e.printStackTrace();
        }
    }
}
