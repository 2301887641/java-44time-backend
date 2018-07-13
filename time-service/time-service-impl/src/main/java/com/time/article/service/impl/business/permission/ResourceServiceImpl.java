package com.time.article.service.impl.business.permission;

import com.time.article.core.service.impl.BaseServiceImpl;
import com.time.article.dao.criteria.business.permission.ResourceCriteria;
import com.time.article.dao.entity.business.permission.Resource;
import com.time.article.dao.mapper.business.permission.ResourceMapper;
import com.time.article.service.api.business.permission.ResourceService;
import com.time.article.service.converter.business.permission.ResourceConverter;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author suiguozhen on 18/07/12
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<
        Integer,
        ResourceDto,
        ResourceCriteriaDto,
        Resource,
        ResourceCriteria,
        ResourceConverter,
        ResourceMapper
        > implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

}
