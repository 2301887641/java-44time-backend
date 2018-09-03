package com.time.article.generator.service.converter;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * converter
 * @author suiguozhen on 18/09/03
 */
@Setter
@Getter
@Component
public class Converter extends BasePojo {
    @Value("${generator.converter.templateName}")
    private String templateName;
}
