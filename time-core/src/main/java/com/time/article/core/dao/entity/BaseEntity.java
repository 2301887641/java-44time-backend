package com.time.article.core.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基类实体
 * @author suiguozhen on 18/06/29
 */
@Getter
@Setter
public class BaseEntity<PK extends Serializable> implements Serializable {
    private PK id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
