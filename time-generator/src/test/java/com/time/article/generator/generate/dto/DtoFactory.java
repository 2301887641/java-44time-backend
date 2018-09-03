package com.time.article.generator.generate.dto;

import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.base.BaseFactory;
import com.time.article.generator.service.dto.Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * dto 工厂类
 * @author suiguozhen on 18/09/01
 */
@Component
public class DtoFactory extends BaseFactory {
    @Autowired
    private Entity entity;
    @Autowired
    private Dto dto;

    @Override
    public void run() {
        dto.setPrimary(entity.getPrimary());
        dto.setColumns(entity.getColumns());
        makeFile(makeDir(dto.getTargetProject())+dto.getDtoName()+dto.getSuffix());
        build(dto,dto.getTemplateName());
    }
}
