package com.time.article.service.converter.business.user;

import com.time.article.core.service.converter.BaseConverter;
import com.time.article.dao.criteria.business.user.UserCriteria;

import com.time.article.dao.entity.business.user.User;
import com.time.article.service.criteria.business.user.UserCriteriaDto;
import com.time.article.service.dto.business.user.UserDto;
import org.mapstruct.Mapper;
/**
 *
 * @author suiguozhen on 2018-09-27
 */
@Mapper
public interface UserConverter extends BaseConverter
        <UserDto,
        UserCriteriaDto,
        User,
        UserCriteria> {
}