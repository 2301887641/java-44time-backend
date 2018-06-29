package com.time.article.service.impl.business;

import com.time.article.dao.mapper.user.UserMapper;
import com.time.article.service.api.business.UserService;
import com.time.article.service.converter.business.user.UserConverter;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author suiguozhen on 18/06/28
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto add() {
        return userConverter.entityToDto(userMapper.select());
    }
}
