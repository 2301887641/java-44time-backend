package ${converterTargetPackage};

import ${baseConverterPackage};
import ${criteriaTargetPackage}.${entityName}Criteria;

import ${entityPackage}.${entityName};
import ${dtoCriteriaTargetPackage}.${dtoCriteriaName};
import ${dtoTargetPackage}.${dtoName};
import org.mapstruct.Mapper;
/**
 *
 * @author ${author} on ${createTime}
 */
@Mapper
public interface ${converterName} extends ${baseConverterName}
        <${dtoName},
        ${dtoCriteriaName},
        ${entityName},
        ${entityName}Criteria> {
}