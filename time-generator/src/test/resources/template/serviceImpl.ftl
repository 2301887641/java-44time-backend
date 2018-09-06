package ${serviceImplTargetPackage};

import ${baseServiceImplPackage};
import ${criteriaTargetPackage}.${entityName}Criteria;
import ${entityPackage}.${entityName};
import ${mapperTargetPackage}.${entityName}Mapper;
import ${implementationInterfacePackage};
import ${converterTargetPackage}.${converterName};
import ${dtoCriteriaTargetPackage}.${dtoCriteriaName};
import ${dtoTargetPackage}.${dtoName};
import org.springframework.stereotype.Service;

/**
 *
 * @author ${author} on ${createTime}
 */
@Service
public class ${serviceImplName} extends ${baseServiceImplName}<
                ${primary},
                ${dtoName},
                ${dtoCriteriaName},
                ${entityName},
                ${entityName}Criteria,
                ${converterName},
                ${entityName}Mapper
                >
        implements ${implementationInterfaceName}
    {

}
