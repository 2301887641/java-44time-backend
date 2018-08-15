package com.time.article.service.impl.business.log;

import com.time.article.core.service.impl.BaseServiceImpl;
import com.time.article.dao.criteria.business.log.OperationLogCriteria;
import com.time.article.dao.entity.business.log.OperationLog;
import com.time.article.dao.mapper.business.log.OperationLogMapper;
import com.time.article.service.api.business.log.OperationLogService;
import com.time.article.service.converter.business.log.OperationLogConverter;
import com.time.article.service.criteria.business.log.OperationLogCriteriaDto;
import com.time.article.service.dto.business.log.OperationLogDto;
import org.springframework.stereotype.Service;

/**
 * 操作日志
 * @author suiguozhen on 18/08/15
 */
@Service
public class OperationLogServiceImpl extends
        BaseServiceImpl<
                Integer,
                OperationLogDto,
                OperationLogCriteriaDto,
                OperationLog,
                OperationLogCriteria,
                OperationLogConverter,
                OperationLogMapper
                >
        implements OperationLogService
    {

}
