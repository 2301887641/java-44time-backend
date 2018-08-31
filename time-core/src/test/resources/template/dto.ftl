package ${targetPackage};

import ${baseDtoPackage};
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ${author} on ${createTime}
 */
@Getter
@Setter
<#if baseDto=="BaseDto">
public class ${dtoName} extends ${baseDto}<${primary}> {
<#else>
public class ${dtoName} extends ${baseDto}<${dtoName},${primary}> {
</#if>
<#list columns as column>
    /**
    *  ${column.remarks}
    */
    private ${column.columnType} ${column.columnName};
</#list>
}
