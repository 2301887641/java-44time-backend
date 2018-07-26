package com.time.article.dao.mapper.business.permission;

import com.time.article.core.dao.mapper.BaseMapper;
import com.time.article.core.dao.mapper.TreeMapper;
import com.time.article.dao.entity.business.permission.Resource;
import org.apache.ibatis.annotations.*;

/**
 * @author suiguozhen on 18/07/12
 */
@Mapper
public interface ResourceMapper extends TreeMapper<Resource,Integer> {
    /**
     * 查询最大右值
     *
     * @return
     */
    @Override
    @Insert("select COALESCE(max(rgt),0) from time_resource")
    Integer selectMaxRgt();

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    @Override
    @Select("select id,rgt,level from time_resource where id=#{id}")
    @Results({
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "rgt",property = "rgt"),
            @Result(column = "level",property = "level")
    })
    Resource selectById(int id);
}
