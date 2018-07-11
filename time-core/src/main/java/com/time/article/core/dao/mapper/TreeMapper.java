package com.time.article.core.dao.mapper;

import com.time.article.core.dao.entity.BaseEntity;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author suiguozhen on 18/04/27
 */
public interface TreeMapper<Entity extends BaseEntity<PK>, PK extends Serializable> extends BaseMapper<Entity,PK>{
    /**
     * 获取最大右值
     * @return
     */
    Integer getMaxRgt();

    /**
     * 增加右值
     * @param number
     * @param rgt
     * @param updateTime
     */
    void increaseRgt(@Param("number") Integer number, @Param("rgt") Integer rgt, @Param("updateTime") LocalDateTime updateTime);

    /**
     * 增加左值
     * @param number
     * @param lft
     * @param updateTime
     */
    void increaseLft(@Param("number") Integer number, @Param("lft") Integer lft, @Param("updateTime") LocalDateTime updateTime);

    /**
     * 减少右值
     * @param number
     * @param rgt
     * @param updateTime
     */
    void subtractRgt(@Param("number") Integer number, @Param("rgt") Integer rgt, @Param("updateTime") LocalDateTime updateTime);

    /**
     * 减少左值
     * @param number
     * @param rgt
     * @param updateTime
     */
    void subtractLft(@Param("number") Integer number, @Param("lft") Integer rgt, @Param("updateTime") LocalDateTime updateTime);

    /**
     * 根据父类id删除
     * @param parentId
     */
    void removeByParentId(PK parentId);
}
