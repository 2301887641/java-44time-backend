package com.time.article.core.dao.mapper;

import com.time.article.core.dao.entity.BaseEntity;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 树形结构mapper
 * @author suiguozhen on 18/04/27
 */
public interface TreeMapper<Entity extends BaseEntity<PK>, PK extends Serializable> extends BaseMapper<Entity,PK>{
    /**
     * 通过like查询path
     * @param id
     * @return
     */
    List<Entity> selectPathByLike(PK id);
}
