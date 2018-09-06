package com.time.article.generator.service.service;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/09/05
 */
@Getter
@Setter
@Component
public class ServiceImpl extends BasePojo {
    @Value("${generator.serviceImpl.templateName}")
    private String templateName;

    private String primary;
    private String criteriaName;
}
