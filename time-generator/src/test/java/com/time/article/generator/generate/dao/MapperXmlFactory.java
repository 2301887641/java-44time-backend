package com.time.article.generator.generate.dao;

import com.time.article.generator.dao.mapper.MapperXml;
import com.time.article.generator.dao.mapper.ResultMap;
import com.time.article.generator.generate.base.BaseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mapper xml
 * @author suiguozhen on 18/08/29
 */
@Component
public class MapperXmlFactory extends BaseFactory {
    @Autowired
    private MapperXml mapperXml;

    @Override
    public void run() {
        makeFile(makeDir(mapperXml.getTargetProject()) + mapperXml.getEntityName() + mapperXml.getMapperXmlSuffix() + mapperXml.getSuffix());
        build(mapperXml, mapperXml.getTemplateName());
    }

    /**
     * 填充数据
     *
     * @param column
     * @param columnType
     */
    public void fill(String column, String columnType, String uppercaseString) {
        //sql中填充字段
        mapperXml.getField().add(column);
        ResultMap resultMap = new ResultMap();
        resultMap.setColumn(column);
        resultMap.setProperty(uppercaseString);
        resultMap.setJdbcType(getJdbcType(columnType));
        mapperXml.getResultMapList().add(resultMap);
    }

    /**
     * 获取jdbcType
     *
     * @param columnType
     * @return
     */
    private String getJdbcType(String columnType) {
        String type = "";
        switch (columnType) {
            case "INT":
            case "TIMESTAMP":
            case "BIT":
            case "TINYINT":
                type = "INTEGER";
                break;
            case "CHAR":
            case "VARCHAR":
                type = "VARCHAR";
                break;
            default:
                type = "VARCHAR";
                break;
        }
        return type;
    }
}
