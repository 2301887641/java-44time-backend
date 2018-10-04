package com.time.article.dao.mapper.business.user;

import com.time.article.core.dao.mapper.BaseMapper;
import com.time.article.dao.entity.business.user.User;

/**
 *  用户mapper
 *
 * @author suiguozhen on 2018-09-27
 */
public interface UserMapper extends BaseMapper<User,Integer> {
    /**
     * 根据用户名查询密码
     * @param username
     * @return
     */
    User selectPasswordByUsername(String username);

    /**
     * 根据手机号查询密码
     * @param mobile
     * @return
     */
    User selectPasswordByMobile(String mobile);
}
