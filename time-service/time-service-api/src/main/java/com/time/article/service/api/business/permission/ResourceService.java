package com.time.article.service.api.business.permission;

import com.time.article.core.service.api.TreeService;
import com.time.article.service.criteria.business.permission.ResourceCriteriaDto;
import com.time.article.service.dto.business.permission.ResourceDto;

/**
 * 资源
 * @author suiguozhen on 18/07/12
 */
public interface ResourceService extends TreeService<ResourceCriteriaDto,ResourceDto,Integer> {
}
