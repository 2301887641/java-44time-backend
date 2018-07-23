package com.time.article.core.service.converter;

import java.util.List;

/**
 * @author suiguozhen on 18/06/29
 */
public interface BaseConverter<DTO,CriteriaDTO,ENTITY,CriteriaENTITY> {
    /**
     * 将ENTITY转换为DTO 单个对象转换
     * @param entity
     * @return
     */
    DTO entityToDto(ENTITY entity);

    /**
     * 将list中的ENTITY转换成DTO
     * @param entities
     * @return
     */
    List<DTO> entityToDto(List<ENTITY> entities);

    /**
     * 将DTO转成ENTITY 单个对象
     * @param DTO
     * @return
     */
    ENTITY dtoToEntity(DTO DTO);

    /**
     * 查询条件DTO 转 查询条件的ENTITY
     * @param criteriaDTO
     * @return
     */
    CriteriaENTITY criteriaDTOToCriteriaEntity(CriteriaDTO criteriaDTO);
}
