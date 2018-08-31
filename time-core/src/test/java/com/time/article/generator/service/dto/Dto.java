package com.time.article.generator.service.dto;

import com.time.article.generator.dao.base.BasePojo;
import com.time.article.generator.dao.entity.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * dto
 * @author suiguozhen on 18/08/31
 */
@Setter
@Getter
@Component
public class Dto extends BasePojo {
    //字段
    private List<Column> columns;
    //生成目录
    @Value("${generator.dto.targetProject}")
    private String targetProject;
    //模板名称
    @Value("${generator.dto.templateName}")
    private String templateName;
}
