package com.time.article.service.dto.business.permission;

import com.time.article.core.controller.annotation.FieldLog;
import com.time.article.core.service.dto.TreeDto;
import com.time.article.dao.enums.business.permission.ResourceEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * 资源dto
 * @author suiguozhen on 18/07/12
 */
@Getter
@Setter
public class ResourceDto extends TreeDto<ResourceDto,Integer> {
    @NotBlank(message = "资源名称不能为空")
    @FieldLog("资源名称:设置为")
    private String name;
    @FieldLog("权限路由:设置为")
    private String code;
    @FieldLog("描述:设置为")
    private String description;
    @NotBlank(message = "资源图标不能为空")
    @FieldLog("资源图标:设置为")
    private String icon;
    @FieldLog("资源类型:设置为")
    @NotBlank(message = "资源类型不能为空")
    private ResourceEnum resourceType;
    @FieldLog("页面访问url:设置为")
    private String url;
}
