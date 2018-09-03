package com.time.article.service.api.business.log;

import com.time.article.core.service.api.BaseService;
import com.time.article.service.criteria.business.log.OperationLogCriteriaDto;
import com.time.article.service.dto.business.log.OperationLogDto;

/**
 * 操作日志
 * @author suiguozhen on 18/08/15
 */
public interface OperationLogService extends BaseService
        <Integer,OperationLogDto, OperationLogCriteriaDto> {

}
