package com.time.article.service.impl.business;

import com.time.article.core.dao.annotation.Custom_Datasource;
import com.time.article.core.message.constant.Constants;
import com.time.article.dao.mapper.business.permission.TestMapper;
import com.time.article.service.api.business.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author suiguozhen on 18/08/12
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testMapper;

    @Custom_Datasource(Constants.DATASOURCE_SECONDARY_NAME)
    @Override
    @Transactional(rollbackFor=Exception.class)
    public void save() {
        testMapper.save();
        int i=1/0;
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void add() {
        testMapper.add();
        int i=1/0;
    }


}
