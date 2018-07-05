package com.time.article.service.impl.business;

import com.time.article.core.service.impl.BaseServiceImpl;
import com.time.article.dao.criteria.business.UserCriteria;
import com.time.article.dao.entity.business.User;
import com.time.article.dao.mapper.user.UserMapper;
import com.time.article.service.api.business.user.UserService;
import com.time.article.service.converter.business.user.UserConverter;
import com.time.article.service.criteria.business.user.UserCriteriaDto;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author suiguozhen on 18/06/28
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl  extends BaseServiceImpl<
        Integer,
        UserDto,
        UserCriteriaDto,
        User,
        UserCriteria,
        UserConverter> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto add() {
        return converter.entityToDto(userMapper.select());
    }
}
