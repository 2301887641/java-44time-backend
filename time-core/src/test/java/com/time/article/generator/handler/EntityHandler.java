package com.time.article.generator.handler;

import com.time.article.core.utils.StringUtils;
import com.time.article.generator.dao.entity.Column;
import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.EntityFactory;
import com.time.article.generator.generate.MapperXmlFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.LinkedList;

/**
 * entity处理器
 *
 * @author suiguozhen on 18/08/28
 */
@Component
@Slf4j
public class EntityHandler extends BaseHandler{
    //数据库信息
    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    //表信息相关 表名
    @Value("${generator.entity.tableName}")
    private String tableName;
    //要忽略的字段
    @Value("${generator.entity.ignoreFields}")
    private String ignoreField;

    //数据库元数据
    private DatabaseMetaData metaData;

    @Autowired
    private Entity entity;
    @Autowired
    private EntityFactory entityFactory;
    @Autowired
    private MapperXmlFactory mapperXmlFactory;

    /**
     * 获取metaData
     */
    private void getMetaData() {
        //数据库连接
        Connection connection;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("数据库驱动连接失败.....请检查");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(url, username, password);
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            log.error("数据库连接失败。。。");
            e.printStackTrace();
        }
    }

    /**
     * entity处理器
     */
    @Override
    public void generate() {
        getMetaData();
        ResultSet result;
        LinkedList<Column> columns = new LinkedList<>();
        try {
            result = metaData.getColumns(null, "%", tableName, "%");
            //忽略字段数组
            String[] ignoreFields = ignoreField.split(",");
            while (result.next()) {
                String column_name = result.getString("COLUMN_NAME");
                String uppercaseString = StringUtils.uppercaseStringIgnoreFirst(column_name);
                String type_name = typeFormat(column_name,result.getString("TYPE_NAME"),uppercaseString);
                //设置主键
                entity.setPrimary(this.getPrimary(column_name, type_name));
                //如果没有在忽略数组中的话
                if (ArrayUtils.contains(ignoreFields, column_name)) {
                    continue;
                }
                //放入column中
                columns.add(Column.of(uppercaseString, type_name, result.getString("REMARKS")));
            }
            entity.setColumns(columns);
            entityFactory.run();
        } catch (SQLException e) {
            log.error("获取表元信息错误。。。");
            e.printStackTrace();
        }
    }

    /**
     * 获取主键类型
     *
     * @param primary
     * @param type_name
     * @return
     */
    private String getPrimary(String primary, String type_name) {
        String type = "Integer";
        if ("id".equals(primary)) {
            if ("VARCHAR".equals(type_name)) {
                type = "String";
            }
        }
        return type;
    }

    /**
     * 格式化字段类型
     * @param column_name       字段名
     * @param type             字段类型
     * @param uppercaseString 字段转大写
     * @return
     */
    private String typeFormat(String column_name,String type,String uppercaseString) {
        String fieldType;
        switch (type) {
            case "BIT":
            case "INT":
            case "TINYINT":
                fieldType = "Integer";
                break;
            case "TIMESTAMP":
                fieldType = "java.time.LocalDateTime";
                break;
            case "VARCHAR":
            case "CHAR":
            case "BIGINT":
                fieldType = "String";
                break;
            default:
                fieldType = "String";
                break;
        }
        //需要填充xml需要的数据
        mapperXmlFactory.fill(column_name,type,uppercaseString);
        return fieldType;
    }

}
