package com.time.article.core.dao.mapper;

import com.time.article.core.dao.entity.BaseEntity;
import com.time.article.core.dao.entity.SearchEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author suiguozhen on 18/04/18
 */
public interface BaseMapper<Entity extends BaseEntity<PK>, PK extends Serializable> {
    /**
     * 分页获取
     *
     * @param searchEntity
     * @return
     */
    List<Entity> getBySearch(SearchEntity searchEntity);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    Entity getById(PK id);

    /**
     * 添加
     *
     * @param data
     * @return
     */
    Integer insert(Entity data);

    /**
     * 修改
     *
     * @param data
     * @return
     */
    Integer update(Entity data);

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    Integer remove(List<PK> ids);

    /**
     * 通过id删除
     * @param id
     * @return
     */
    Integer remove(PK id);
}
