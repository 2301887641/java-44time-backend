package ${converterTargetProject};

import ${baseConverterTargetPackage};
import ${criteriaTargetPackage};

import ${entityPackage};
import ${dtoCriteriaTargetPackage};
import ${dtoTargetPackage};

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