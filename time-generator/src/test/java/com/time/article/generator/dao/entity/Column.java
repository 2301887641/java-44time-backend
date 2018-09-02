package com.time.article.generator.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 表字段 输出 放入集合中
 * @author suiguozhen on 18/08/27
 */
@Getter
@Setter
@AllArgsConstructor(staticName = "of")
public class Column {
    /**字段名*/
    private String columnName;
    /**字段名称*/
    private String columnType;
    /**注释*/
    private String remarks;
}
