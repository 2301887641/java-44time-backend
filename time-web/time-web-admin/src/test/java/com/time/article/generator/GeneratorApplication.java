package com.time.article.generator;

import lombok.extern.slf4j.Slf4j;

/**
 * 代码生成器
 * @author suiguozhen on 18/08/20
 */
@Slf4j
public class GeneratorApplication {
    //数据库连接
    private static String JDBC_URL="jdbc:mysql://localhost:3306/test";
    //用户名
    private static String USERNAME="root";
    //密码
    private static String PASSWORD="123456";
    //mysql驱动
    private static String DRIVER="com.mysql.jdbc.Driver";
    //项目目录  d://44time
    private static String PROJECT_NAME=System.getProperty("user.dir");


    /**启动类*/
    public static void main(String[] args) {

    }

    private static void generateDao(String tableName){


    }


}
