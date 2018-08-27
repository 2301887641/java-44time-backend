package com.time.article.generator.dao;

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
    private String columnName;
    private String columnType;
    private String remarks;
}
