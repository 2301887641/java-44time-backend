package com.time.article.core.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author suiguozhen on 18/04/23
 */
@Getter
@Setter
public class TreeEntity<ENTITY extends TreeEntity<ENTITY, PK>, PK extends Serializable> extends BaseEntity<PK> {
    private Integer level;
    private Integer lft;
    private Integer rgt;
    private Integer priority;
    private ENTITY parent;
}
