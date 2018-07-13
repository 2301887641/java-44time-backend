package com.time.article.core.service.impl;

import com.time.article.core.dao.entity.BaseEntity;
import com.time.article.core.dao.mapper.BaseMapper;
import com.time.article.core.service.api.BaseService;
import com.time.article.core.service.converter.BaseConverter;
import com.time.article.core.service.dto.BaseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * @author suiguozhen on 18/06/30
 */

@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<
        PK extends Serializable,
        DTO extends BaseDto<PK>,
        CriteriaDTO extends DTO,
        ENTITY extends BaseEntity<PK>,
        CriteriaENTITY extends ENTITY,
        CONVERTER extends BaseConverter<DTO,CriteriaDTO,ENTITY,CriteriaENTITY>,
        MAPPER extends BaseMapper<ENTITY, PK>
        >
        implements BaseService<PK,DTO,CriteriaDTO> {

    @Autowired
    protected MAPPER mapper;

    protected CONVERTER converter;

    public BaseServiceImpl(){
        Class<CONVERTER> clazz = (Class<CONVERTER>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[5];
        converter = Mappers.getMapper(clazz);
    }

    /**
     * 添加操作
     *
     * @param dto
     * @return
     */
    @Override
    public PK insert(DTO dto) {
        return  mapper.insert(converter.dtoToEntity(dto));
    }
}
