package com.time.article.generator;

import com.time.article.generator.dao.Column;
import com.time.article.generator.dao.Entity;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;

/**
 * 代码生成器
 * @author suiguozhen on 18/08/27
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
public class GeneratorApplication {
    //数据库信息
    @Value("${spring.datasource.driverClassName}")
    private String driver;
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    //表信息 忽略字段
    @Value("${generator.entity.tableName}")
    private String tableName;
    @Value("${generator.entity.ignoreFields}")
    private String ignoreField;
    @Value("${generator.entity.author}")
    private String author;
    @Value("${generator.entity.entityName")
    private String entityName;
    @Value("${generator.entity.packageName}")
    private String packageName;
    @Value("${generator.entity.BaseEntityPackage")
    private String baseEntityPackage;


    //数据库元数据
    private DatabaseMetaData metaData;

    /**
     * 先获取元数据
     */
    @Before
    public void getMetaData(){
        //数据库连接
        Connection connection=null;
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            log.error("数据库驱动连接失败.....请检查");
            e.printStackTrace();
        }
        try {
            connection= DriverManager.getConnection(url,username,password);
            metaData = connection.getMetaData();
        } catch (SQLException e) {
            log.error("数据库连接失败。。。");
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        ResultSet result = null;
        Entity entity = new Entity();
        LinkedList<Column> columns = new LinkedList<>();
        try {
            result = metaData.getColumns(null, "%", tableName, "%");
            //忽略字段数组
            String[] ignoreFields = ignoreField.split(",");
            while(result.next()){
                String column_name = result.getString("COLUMN_NAME");
                String type_name = result.getString("TYPE_NAME");
                if("id".equals(column_name)){
                    switch(type_name){
                        case "INT":
                            entity.setPrimary("Integer");
                            break;
                        case "VARCHAR":
                            entity.setPrimary("String");
                            break;
                        default:
                            entity.setPrimary("Integer");
                            break;
                    }
                }
                //如果没有在忽略数组中的话
                if(ArrayUtils.contains(ignoreFields, column_name)){
                   continue;
                }
                //放入column中
                columns.add(Column.of(column_name,type_name,result.getString("REMARKS")));
            }
            entity.setColumns(columns);
            entity.setAuthor(author);
            entity.setEntityName(entityName);
            entity.setPackageName(packageName);
            entity.setBaseEntityPackage(baseEntityPackage);
            entity.setCreateTime(LocalDate.now().toString());
        } catch (SQLException e) {
            log.error("获取表元信息错误。。。");
            e.printStackTrace();
        }

    }
}
