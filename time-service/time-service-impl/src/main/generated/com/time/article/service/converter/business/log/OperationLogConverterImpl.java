package com.time.article.service.converter.business.log;

import com.time.article.dao.criteria.business.log.OperationLogCriteria;
import com.time.article.dao.entity.business.log.OperationLog;
import com.time.article.service.criteria.business.log.OperationLogCriteriaDto;
import com.time.article.service.dto.business.log.OperationLogDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-12-06T14:19:14+0800",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_51 (Oracle Corporation)"
)
public class OperationLogConverterImpl implements OperationLogConverter {

    @Override
    public OperationLogDto entityToDto(OperationLog arg0) {
        if ( arg0 == null ) {
            return null;
        }

        OperationLogDto operationLogDto = new OperationLogDto();

        operationLogDto.setId( arg0.getId() );
        operationLogDto.setTitle( arg0.getTitle() );
        operationLogDto.setContent( arg0.getContent() );
        operationLogDto.setIp( arg0.getIp() );
        operationLogDto.setClassName( arg0.getClassName() );
        operationLogDto.setMethod( arg0.getMethod() );
        operationLogDto.setResult( arg0.getResult() );
        operationLogDto.setUserId( arg0.getUserId() );
        operationLogDto.setLogType( arg0.getLogType() );

        return operationLogDto;
    }

    @Override
    public List<OperationLogDto> entityToDto(List<OperationLog> arg0) {
        if ( arg0 == null ) {
            return null;
        }

        List<OperationLogDto> list = new ArrayList<OperationLogDto>( arg0.size() );
        for ( OperationLog operationLog : arg0 ) {
            list.add( entityToDto( operationLog ) );
        }

        return list;
    }

    @Override
    public OperationLog dtoToEntity(OperationLogDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        OperationLog operationLog = new OperationLog();

        operationLog.setId( arg0.getId() );
        operationLog.setTitle( arg0.getTitle() );
        operationLog.setContent( arg0.getContent() );
        operationLog.setIp( arg0.getIp() );
        operationLog.setClassName( arg0.getClassName() );
        operationLog.setMethod( arg0.getMethod() );
        operationLog.setResult( arg0.getResult() );
        operationLog.setUserId( arg0.getUserId() );
        operationLog.setLogType( arg0.getLogType() );

        return operationLog;
    }

    @Override
    public OperationLogCriteria criteriaDTOToCriteriaEntity(OperationLogCriteriaDto arg0) {
        if ( arg0 == null ) {
            return null;
        }

        OperationLogCriteria operationLogCriteria = new OperationLogCriteria();

        operationLogCriteria.setId( arg0.getId() );
        operationLogCriteria.setTitle( arg0.getTitle() );
        operationLogCriteria.setContent( arg0.getContent() );
        operationLogCriteria.setIp( arg0.getIp() );
        operationLogCriteria.setClassName( arg0.getClassName() );
        operationLogCriteria.setMethod( arg0.getMethod() );
        operationLogCriteria.setResult( arg0.getResult() );
        operationLogCriteria.setUserId( arg0.getUserId() );
        operationLogCriteria.setLogType( arg0.getLogType() );

        return operationLogCriteria;
    }
}
