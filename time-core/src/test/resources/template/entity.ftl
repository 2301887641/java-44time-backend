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
public class ${entityName} extends ${baseEntityName}<${primary}> {
<#list columns as column>
    /**
    *  ${column.remarks}
    */
    private ${column.columnType} ${column.columnName};
</#list>
}