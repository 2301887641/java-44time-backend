package com.time.article.dao.mapper.business.permission;

import com.time.article.core.dao.mapper.BaseMapper;
import com.time.article.dao.entity.business.permission.Resource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suiguozhen on 18/07/12
 */
@Mapper
public interface ResourceMapper extends BaseMapper<Resource,Integer> {
}
