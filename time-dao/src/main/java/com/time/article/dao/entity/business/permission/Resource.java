package com.time.article.dao.entity.business.permission;

import com.time.article.core.dao.entity.TreeEntity;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author suiguozhen on 18/03/28
 */
@Getter
@Setter
public class Resource extends TreeEntity<Resource,Integer> {
    private String name;
    private String code;
    private String description;
    private String icon;
    private ResourceEnum resourceType;
    private String url;
}
