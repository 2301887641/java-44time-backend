package com.time.article.service.converter.business.log;

import com.time.article.core.service.converter.BaseConverter;
import com.time.article.dao.criteria.business.log.OperationLogCriteria;
import com.time.article.dao.entity.business.log.OperationLog;
import com.time.article.service.criteria.business.log.OperationLogCriteriaDto;
import com.time.article.service.dto.business.log.OperationLogDto;
import org.mapstruct.Mapper;

/**
 * 操作日志转换
 *
 * @author suiguozhen on 18/08/15
 */
@Mapper
public interface OperationLogConverter extends BaseConverter
        <OperationLogDto,
        OperationLogCriteriaDto,
        OperationLog,
        OperationLogCriteria> {
}