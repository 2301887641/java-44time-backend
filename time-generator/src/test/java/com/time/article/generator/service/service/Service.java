package com.time.article.generator.service.service;

import com.time.article.generator.dao.base.BasePojo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * service pojo
 * @author suiguozhen on 18/09/03
 */
@Component
public class Service extends BasePojo {
    //模板名称
    @Value("${generator.service.templateName}")
    private String templateName;
}
