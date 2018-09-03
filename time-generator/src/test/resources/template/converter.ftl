package ${converterTargetPackage};

import ${baseConverterPackage};
import ${criteriaTargetPackage}.${entityName}Criteria;

import ${entityPackage}.${entityName};
import ${dtoCriteriaTargetPackage}.${dtoCriteriaName};
import ${dtoTargetPackage}.${dtoName};

/**
 *
 * @author ${author} on ${createTime}
 */
public interface ${converterName} extends ${baseConverterName}
        <${dtoName},
        ${dtoCriteriaName},
        ${entityName},
        ${entityName}Criteria> {
}