package com.time.article.core.service.converter;


import org.mapstruct.Mapping;

/**
 * @author suiguozhen on 18/04/23
 */
public interface TreeConverter<DTO,CriteriaDto,ENTITY,CriteriaEntity> extends BaseConverter<DTO,CriteriaDto,ENTITY,CriteriaEntity> {

    /**
     *  单个将entity转换为Dto对象转换
     *
     * @param entity
     * @return
     */
    @Override
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "parentName", source = "parent.name")
    DTO entityToDto(ENTITY entity);

    /**
     * 将dto转成entity 单个对象
     *
     * @param dto
     * @return
     */
    @Override
    @Mapping(target = "parent.id",source = "parentId")
    ENTITY dtoToEntity(DTO dto);
}
