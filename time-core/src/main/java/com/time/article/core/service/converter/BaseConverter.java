package com.time.article.core.service.converter;

/**
 * @author suiguozhen on 18/06/29
 */
public interface BaseConverter<Dto,CriteriaDto,Entity,CriteriaEntity> {
    Dto entityToDto(Entity entity);
    Entity dtoToEntity(Dto dto);
}
