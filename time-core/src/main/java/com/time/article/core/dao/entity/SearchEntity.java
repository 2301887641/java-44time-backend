package com.time.article.core.dao.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author suiguozhen on 18/04/21
 */
@Getter
@Setter
public class SearchEntity<T> implements Serializable {
    private String orderBy;
    private T entity;

    private SearchEntity() {
    }

    private SearchEntity(String orderBy, T entity) {
        this.entity = entity;
        this.orderBy = orderBy;
    }

    public static <W extends BaseEntity> SearchEntity of(String orderBy, W entity) {
        return new SearchEntity<>(orderBy,entity);
    }
}
