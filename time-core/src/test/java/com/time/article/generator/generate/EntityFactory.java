package com.time.article.generator.generate;

import com.time.article.generator.dao.entity.Entity;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author suiguozhen on 18/08/27
 */
@Slf4j
@Component
public class EntityFactory extends BaseFactory {
    @Value("${generator.entity.basePackagePath}")
    private String basePackagePath;
    @Value("${generator.entity.templateName}")
    private String templateName;
    @Value("${generator.entity.targetPackage}")
    private String targetPackage;
    @Value("${generator.entity.targetProject}")
    private String targetProject;
    @Value("${generator.entity.entityName}")
    private String entityName;
    //java后缀
    private String suffix = ".java";
    //最终要写出的文件
    private String generatedFileName;

    @Override
    public void init() {
        File project = new File(targetProject);
        if (!project.exists()) {
            log.info("正在递归生成目录。。。。");
            project.mkdirs();
        }
        generatedFileName = project.getAbsolutePath() + "\\" + entityName + suffix;
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

    @Override
    public void create(Entity entity) {
        init();
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        //设置模板路径
        configuration.setClassForTemplateLoading(EntityFactory.class, basePackagePath);
        //设置默认字体
        configuration.setDefaultEncoding("utf-8");
        Template template = null;
        FileWriter fileWriter = null;
        //获取模板
        try {
            template = configuration.getTemplate(templateName);
            fileWriter = new FileWriter(generatedFileName);
        } catch (IOException e) {
            log.error("获取模板失败");
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
