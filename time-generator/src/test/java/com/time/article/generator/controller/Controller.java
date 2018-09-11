package com.time.article.generator.controller;

import com.time.article.generator.dao.base.BasePojo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author suiguozhen on 18/09/08
 */
@Getter
@Setter
@Component
public class Controller extends BasePojo {
    @Value("${generator.controller.templateName}")
    private String templateName;
    @Value("${generator.controller.routerName}")
    private String routerName;
}
