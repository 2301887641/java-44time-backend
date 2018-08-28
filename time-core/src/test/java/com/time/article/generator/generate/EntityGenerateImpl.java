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
public class EntityGenerateImpl extends BaseGenerate {
    @Value("${generator.entity.basePackagePath}")
    private String basePackagePath;
    @Value("${generator.entity.templateName}")
    private String templateName;
    @Value("${generator.entity.targetPackage}")
    private String targetPackage;
    @Value("${generator.entity.targetProject}")
    private String targetProject;

    @Override
    public void checkDir() {
        File project = new File(targetProject);
        File parentFile = project.getParentFile();
        if (!parentFile.exists()) {
            log.info("正在生成父目录。。。。");
            parentFile.mkdirs();
        }
        try {
            project.createNewFile();
        } catch (IOException e) {
            log.error("创建entity文件失败.....");
            e.printStackTrace();
        }
    }

    @Override
    public void create(Entity entity) {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        //设置模板路径
        configuration.setClassForTemplateLoading(EntityGenerateImpl.class, basePackagePath);
        //设置默认字体
        configuration.setDefaultEncoding("utf-8");
        Template template = null;
        FileWriter fileWriter = null;
        //获取模板
        try {
            template = configuration.getTemplate(templateName);
            fileWriter = new FileWriter(targetProject);
        } catch (IOException e) {
            log.error("获取entity模板失败");
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
