package com.time.article.core.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
    private Integer level;
    private List<DTO> children;
    private PK parentId;
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
