package com.time.article.generator.dao.mapper;

import lombok.Getter;
import lombok.Setter;

/**
 * 针对resultMap的封装
 * @author suiguozhen on 18/08/30
 */
@Getter
@Setter
public class ResultMap {
    //property属性
    private String property;
    //column属性
    private String column;
    //jdbcType属性
    private String jdbcType;
}
