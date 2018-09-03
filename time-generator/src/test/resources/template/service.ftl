package ${serviceTargetPackage};

import ${baseServicePackage};
import ${dtoCriteriaTargetPackage}.${dtoCriteriaName};
import ${dtoTargetPackage}.${dtoName};

/**
 *
 * @author ${author} on ${createTime}
 */
public interface ${serviceName} extends ${baseServiceName}
<#if baseServiceName=="BaseService">
        <${primary},${dtoName}, ${dtoCriteriaName}> {
<#else>
        <${dtoCriteriaName},${dtoName},${primary}> {
</#if>
}
