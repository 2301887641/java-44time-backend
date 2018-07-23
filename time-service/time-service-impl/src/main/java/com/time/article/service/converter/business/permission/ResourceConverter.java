package com.time.article.service.converter.business.permission;

import com.time.article.core.service.converter.TreeConverter;
import com.time.article.dao.criteria.business.permission.ResourceCriteria;
import com.time.article.dao.entity.business.permission.Resource;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.mapstruct.Mapper;

/**
 * @author suiguozhen on 18/07/12
 */
@Mapper
public interface ResourceConverter extends TreeConverter<
        ResourceDto,
        ResourceCriteriaDto,
        Resource,
        ResourceCriteria
        > {
}
