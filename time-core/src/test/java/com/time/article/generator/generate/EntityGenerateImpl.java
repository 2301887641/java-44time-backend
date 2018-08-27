package com.time.article.generator.generate;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @author suiguozhen on 18/08/27
 */
@Slf4j
public class EntityGenerateImpl extends BaseGenerate {
    private String basePackagePath="/template/entity";
    private String templateName="entity.ftl";

    public EntityGenerateImpl(String basePackagePath, String templateName) {
        this.basePackagePath = basePackagePath;
        this.templateName = templateName;
    }

    @Override
    public void create() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        //设置模板路径
        configuration.setClassForTemplateLoading(EntityGenerateImpl.class, basePackagePath);
        //设置默认字体
        configuration.setDefaultEncoding("utf-8");
        Template template = null;
        //获取模板
        try {
            template = configuration.getTemplate(templateName);
        } catch (IOException e) {
            log.error("获取entity模板失败");
            e.printStackTrace();
        }
        //设置数据
        User user = new User
        //设置输出到哪个文件
        File file = new File("C:/Users/Administrator/Desktop/test/src/main/resources/result/result.html");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fileWriter = new FileWriter(file);
        template.process(user, fileWriter);
    }
}
