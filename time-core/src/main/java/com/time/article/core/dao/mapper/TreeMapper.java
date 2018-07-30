package com.time.article.core.dao.mapper;

import com.time.article.core.dao.entity.BaseEntity;
import org.apache.ibatis.annotations.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author suiguozhen on 18/04/27
 */
public interface TreeMapper<Entity extends BaseEntity<PK>, PK extends Serializable> extends BaseMapper<Entity,PK>{
    /**
     * 查询最大右值 使用COALESCE来避免null
     * @return
     */
    int selectMaxRgt();

}
