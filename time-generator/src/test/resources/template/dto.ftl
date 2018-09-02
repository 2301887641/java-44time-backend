package ${dtoTargetPackage};

import ${baseDtoPackage};
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ${author} on ${createTime}
 */
@Getter
@Setter
<#if baseDtoName=="BaseDto">
public class ${dtoName} extends ${baseDtoName}<${primary}> {
<#else>
public class ${dtoName} extends ${baseDtoName}<${dtoName},${primary}> {
</#if>
<#list columns as column>
    /**
    *  ${column.remarks}
    */
    private ${column.columnType} ${column.columnName};
</#list>
}
