package com.time.article.core.service.impl;

import com.time.article.core.dao.entity.BaseEntity;
import com.time.article.core.dao.entity.SearchEntity;
import com.time.article.core.dao.mapper.BaseMapper;
import com.time.article.core.service.api.BaseService;
import com.time.article.core.service.converter.BaseConverter;
import com.time.article.core.service.dto.BaseDto;
import com.time.article.core.utils.PaginationUtils;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * 基类实现
 * @author suiguozhen on 18/06/30
 */

@Transactional(rollbackFor = Exception.class)
public class BaseServiceImpl<
        PK extends Serializable,
        DTO extends BaseDto<PK>,
        CriteriaDTO extends DTO,
        ENTITY extends BaseEntity<PK>,
        CriteriaENTITY extends ENTITY,
        CONVERTER extends BaseConverter<DTO, CriteriaDTO, ENTITY, CriteriaENTITY>,
        MAPPER extends BaseMapper<ENTITY, PK>
        >
        implements BaseService<PK, DTO, CriteriaDTO> {

    @Autowired
    protected MAPPER mapper;

    protected CONVERTER converter;

    public BaseServiceImpl() {
        Class<CONVERTER> clazz = (Class<CONVERTER>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[5];
        converter = Mappers.getMapper(clazz);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @Override
    public DTO getById(Integer id) {
        return converter.entityToDto(mapper.selectById(id));
    }

    /**
     * 添加操作
     *
     * @param dto
     * @return
     */
    @Override
    public PK insert(DTO dto){
        return mapper.insert(converter.dtoToEntity(dto));
    }

    @Override
    public List<DTO> getList(CriteriaDTO criteriaDto) {
        return converter.entityToDto(mapper.selectBySearch(SearchEntity.of(PaginationUtils.getOrderBy(), converter.criteriaDTOToCriteriaEntity(criteriaDto))));
    }

    /**
     * 修改
     *
     * @param dto
     */
    @Override
    public void update(DTO dto) {
        mapper.update(converter.dtoToEntity(dto));
    }

    /**
     * 删除单个id
     * @param id
     * @return
     */
    @Override
    public PK delete(PK id) {
        return mapper.delete(id);
    }
}
