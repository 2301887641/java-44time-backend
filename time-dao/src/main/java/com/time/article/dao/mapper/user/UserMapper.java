package com.time.article.dao.mapper.user;

import com.time.article.dao.entity.business.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author suiguozhen on 18/06/29
 */
@Mapper
public interface UserMapper {
    User select();
}
