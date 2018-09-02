package ${entityPackage};

import ${baseEntityPackage};
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ${author} on ${createTime}
 */
@Getter
@Setter
<#if baseEntityName=="TreeEntity">
public class ${entityName} extends ${baseEntityName}<${entityName},${primary}> {

<#else>
public class ${entityName} extends ${baseEntityName}<${primary}> {

</#if>
<#list columns as column>
    /**
    *  ${column.remarks}
    */
    private ${column.columnType} ${column.columnName};
</#list>
}