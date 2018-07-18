package com.time.article.core.service.converter;

import java.util.List;

/**
 * @author suiguozhen on 18/06/29
 */
public interface BaseConverter<Dto,CriteriaDTO,Entity,CriteriaEntity> {
    /**
     * 将entity转换为Dto 单个对象转换
     * @param entity
     * @return
     */
    Dto entityToDto(Entity entity);

    /**
     * 将list中的entity转换成dto
     * @param entity
     * @return
     */
    List<Dto> entityToDto(List<Entity> entity);

    /**
     * 将dto转成entity 单个对象
     * @param dto
     * @return
     */
    Entity dtoToEntity(Dto dto);

    /**
     * 查询条件DTO 转 查询条件的Entity
     * @param criteriaDto
     * @return
     */
    CriteriaEntity criteriaDtoToCriteriaEntity(CriteriaDTO criteriaDto);
}
