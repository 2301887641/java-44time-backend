package com.time.article.service.dto.business.permission;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.time.article.core.service.dto.TreeDto;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author suiguozhen on 18/07/12
 */
@Getter
@Setter
public class ResourceDto extends TreeDto<ResourceDto,Integer> {
    @NotBlank(message = "资源名称不能为空")
    private String name;
    private String code;
    private String description;
    @NotBlank(message = "资源图标不能为空")
    private String icon;
    private ResourceEnum resourceType;
    private String url;
    private Integer parentId;
}
