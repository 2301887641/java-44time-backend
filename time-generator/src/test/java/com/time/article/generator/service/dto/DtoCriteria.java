package com.time.article.generator.service.dto;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * dtoCriteria
 * @author suiguozhen on 18/09/01
 */
@Setter
@Getter
@Component
public class DtoCriteria extends BasePojo {
    //模板名称
    @Value("${generator.dtoCriteria.templateName}")
    private  String templateName;
}
