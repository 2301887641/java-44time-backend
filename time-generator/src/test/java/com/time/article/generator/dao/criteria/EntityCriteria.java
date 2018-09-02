package com.time.article.generator.dao.criteria;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * criteria生成器实体
 * @author suiguozhen on 18/08/29
 */
@Getter
@Setter
@Component
public class EntityCriteria extends BasePojo {
    //项目生成目录
    @Value("${generator.criteria.targetProject}")
    private String targetProject;

    //模板名称
    @Value("${generator.criteria.templateName}")
    private String templateName;
}
