package com.time.article.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author suiguozhen on 18/04/23
 */
@Getter
@Setter
@ToString(exclude = "children")
public class TreeDto<DTO extends TreeDto, PK extends Serializable> extends BaseDto<PK> {
    @JsonIgnore
    private Integer lft;
    @JsonIgnore
    private Integer rgt;
    @JsonIgnore
    private Integer level;
    /**children为null的不返回 前端界面不让返回null*/
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<DTO> children;
    private PK parentId;
    @JsonIgnore
    private Integer priority;
    @JsonIgnore
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
