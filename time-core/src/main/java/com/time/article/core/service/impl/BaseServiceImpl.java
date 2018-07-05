package com.time.article.core.service.impl;

import com.time.article.core.service.api.BaseService;
import com.time.article.core.service.converter.BaseConverter;
import com.time.article.core.service.dto.BaseDto;
import org.mapstruct.factory.Mappers;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;

/**
 * @author suiguozhen on 18/06/30
 */

@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<PK,
        DTO extends BaseDto<PK>,
        CriteriaDTO,
        ENTITY,
        CriteriaENTITY,
        CONVERTER extends BaseConverter<DTO,CriteriaDTO,ENTITY,CriteriaENTITY>>
        implements BaseService<PK,DTO> {

    protected CONVERTER converter;

    public BaseServiceImpl(){
        Class<CONVERTER> clazz = (Class<CONVERTER>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[5];
        converter = Mappers.getMapper(clazz);
    }

    @Override
    public DTO add() {
        return null;
    }
}
