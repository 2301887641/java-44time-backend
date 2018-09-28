package com.time.article.service.api.business.user;

import com.time.article.core.service.api.BaseService;
import com.time.article.service.criteria.business.user.UserCriteriaDto;
import com.time.article.service.dto.business.user.UserDto;

/**
 * 用户service
 * @author suiguozhen on 2018-09-27
 */
public interface UserService extends BaseService
        <Integer, UserDto, UserCriteriaDto> {
    /**
     * 根据用户名查询密码
     *
     * @param username
     * @return
     */
    UserDto selectPasswordByName(String username);
}
