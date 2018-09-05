package ${serviceImplTargetProject};

import ${baseServiceImplPackage};
import com.time.article.dao.criteria.business.log.OperationLogCriteria;
import com.time.article.dao.entity.business.log.OperationLog;
import com.time.article.dao.mapper.business.log.OperationLogMapper;
import ${implementationInterfacePackage};
import com.time.article.service.converter.business.log.OperationLogConverter;
import com.time.article.service.criteria.business.log.OperationLogCriteriaDto;
import com.time.article.service.dto.business.log.OperationLogDto;
import org.springframework.stereotype.Service;

/**
 *
 * @author ${author} on ${creatTime}
 */
@Service
public class ${serviceImplName} extends
        ${baseServiceImplName}<
                ${primary},
                OperationLogDto,
                OperationLogCriteriaDto,
                OperationLog,
                OperationLogCriteria,
                OperationLogConverter,
                OperationLogMapper
                >
        implements ${implementationInterfaceName}
    {

}
