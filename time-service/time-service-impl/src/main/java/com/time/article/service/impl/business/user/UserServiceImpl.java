package com.time.article.service.impl.business.user;

import com.time.article.core.service.impl.BaseServiceImpl;
import com.time.article.dao.criteria.business.user.UserCriteria;
import com.time.article.dao.entity.business.user.User;
import com.time.article.dao.mapper.business.user.UserMapper;
import com.time.article.service.api.business.user.UserService;
import com.time.article.service.converter.business.user.UserConverter;
import com.time.article.service.criteria.business.user.UserCriteriaDto;
import com.time.article.service.dto.business.user.UserDto;
import org.springframework.stereotype.Service;

/**
 * 用户
 * @author suiguozhen on 2018-09-27
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<
                Integer,
                UserDto,
                UserCriteriaDto,
                User,
                UserCriteria,
                UserConverter,
                UserMapper
                >
        implements UserService
    {


        /**
         * 根据用户名查询密码
         *
         * @param username
         * @return
         */
        @Override
        public UserDto selectPasswordByName(String username) {
            return  converter.entityToDto(mapper.selectPasswordByUsername(username));
        }

        /**
         * 根据手机号登陆
         *
         * @param mobile
         * @return
         */
        @Override
        public UserDto selectPasswordByMobile(String mobile) {
            return  converter.entityToDto(mapper.selectPasswordByMobile(mobile));
        }
    }