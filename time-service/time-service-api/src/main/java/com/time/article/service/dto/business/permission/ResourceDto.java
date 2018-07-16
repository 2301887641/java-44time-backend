package com.time.article.service.dto.business.permission;

import com.time.article.core.service.dto.BaseDto;
import com.time.article.core.service.dto.TreeDto;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author suiguozhen on 18/07/12
 */
@Getter
@Setter
public class ResourceDto extends TreeDto<ResourceDto,Integer> {
    private String name;
    private String code;
    private String description;
    private String icon;
    private ResourceEnum resourceType;
    private String url;
}
