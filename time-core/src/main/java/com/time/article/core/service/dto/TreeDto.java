package com.time.article.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.time.article.core.controller.annotation.FieldLog;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suiguozhen on 18/04/23
 */
@Getter
@Setter
public class TreeDto<DTO extends TreeDto, PK extends Serializable> extends BaseDto<PK> {
    @JsonIgnore
    private Integer level;
    @JsonIgnore
    private String path;
    /**children为null的不返回 前端界面不让返回null*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DTO> children;
    @FieldLog("父类id:设置为")
    @NotNull(message = "请传递父类id")
    private PK parentId;
    @JsonIgnore
    @FieldLog("优先级:设置为")
    private Integer priority;
    @JsonIgnore
    @FieldLog("父类名称:设置为")
    private String parentName;
    /**
     * children的添加方法
     *
     * @param dto
     */
    public void addChildren(DTO dto) {
        if (children == null) {
            children = new ArrayList<>();
        }
        children.add(dto);
    }
}
