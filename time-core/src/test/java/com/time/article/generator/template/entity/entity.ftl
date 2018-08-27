package ${packageName};

import ${baseEntityPackage};
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author ${author} on ${createTime}
 */
@Getter
@Setter
public class ${entityName} extends BaseEntity<${primary}> {
    /**
    *  ${remarks}
    */
    private ${columnType} ${columnName};
    }