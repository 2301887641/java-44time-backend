package com.time.article.generator.handler;

import com.time.article.core.utils.StringUtils;
import com.time.article.generator.dao.entity.Column;
import com.time.article.generator.dao.entity.Entity;
import com.time.article.generator.generate.BaseFactory;
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
public class EntityHandler {
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
    private BaseFactory baseGenerate;

    /**
     * 获取metaData
     */
    private void getMetaData() {
        //数据库连接
        Connection connection = null;
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
    public void entityHandler() {
        getMetaData();
        ResultSet result;
        LinkedList<Column> columns = new LinkedList<>();
        try {
            result = metaData.getColumns(null, "%", tableName, "%");
            //忽略字段数组
            String[] ignoreFields = ignoreField.split(",");
            while (result.next()) {
                String column_name = nameFormat(result.getString("COLUMN_NAME"));
                String type_name = typeFormat(result.getString("TYPE_NAME"));
                //设置主键
                entity.setPrimary(this.getPrimary(column_name, type_name));
                //如果没有在忽略数组中的话
                if (ArrayUtils.contains(ignoreFields, column_name)) {
                    continue;
                }
                //放入column中
                columns.add(Column.of(column_name, type_name, result.getString("REMARKS")));
            }
            entity.setColumns(columns);
            baseGenerate.create(entity);
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
    public String getPrimary(String primary, String type_name) {
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
     *
     * @param type
     * @return
     */
    public String typeFormat(String type) {
        String fieldType;
        switch (type) {
            case "INT":
                fieldType = "Integer";
                break;
            case "VARCHAR":
                fieldType = "String";
                break;
            case "CHAR":
                fieldType = "String";
                break;
            case "DATE":
                fieldType = "Date";
                break;
            case "BIGINT":
                fieldType = "Long";
                break;
            default:
                fieldType = "String";
                break;
        }
        return fieldType;
    }

    /**
     * 格式化字段名
     * @param name
     * @return
     */
    private String nameFormat(String name) {
        if (name.contains("_")) {
            String[] splits = name.split("_");
            StringBuilder stringBuilder = new StringBuilder(splits[0]);
            for (int i = 1; i < splits.length; i++) {
                stringBuilder.append(StringUtils.firstToUpperCase(splits[i]));
            }
            return stringBuilder.toString();
        }
        return name;
    }
}
