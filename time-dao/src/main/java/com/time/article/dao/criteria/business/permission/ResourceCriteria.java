package com.time.article.dao.criteria.business.permission;

import com.time.article.dao.entity.business.permission.Resource;
import lombok.Getter;
import lombok.Setter;

/**
 * @author suiguozhen on 18/07/12
 */
@Getter
@Setter
public class ResourceCriteria extends Resource {
    private Integer ignoreId;
}
