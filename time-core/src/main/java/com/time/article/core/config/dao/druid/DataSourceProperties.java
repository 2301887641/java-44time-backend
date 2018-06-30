package com.time.article.core.config.dao.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author suiguozhen on 18/06/30
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperties {
    /**
     * 前缀 批量赋值druid需要这个前缀
     */
    private static final String PREFIX = "druid.";
    /**
     * 数据库配置
     */
    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private String initialSize;
    private String minIdle;
    private String maxActive;
    private String maxWait;
    private String timeBetweenEvictionRunsMillis;
    private String minEvictableIdleTimeMillis;
    private String validationQuery;
    private String testWhileIdle;
    private String testOnBorrow;
    private String testOnReturn;
    private String filters;
    private String logSlowSql;
    private String poolPreparedStatements;
    private String maxPoolPreparedStatementPerConnectionSize;
    private String useGlobalDataSourceStat;

    @Value("${spring.datasource.connectionProperties.druid.stat.mergeSql}")
    private String mergeSql;
    @Value("${spring.datasource.connectionProperties.druid.stat.slowSqlMillis}")
    private String slowSqlMillis;

    /**
     * 同意设置druid需要的连接属性
     *
     * @return
     */
    public Properties getDruidProperties() {
        Properties properties = new Properties();
        properties.setProperty(PREFIX + "driverClassName", driverClassName);
        properties.setProperty(PREFIX + "url", url);
        properties.setProperty(PREFIX + "username", username);
        properties.setProperty(PREFIX + "password", password);
        properties.setProperty(PREFIX + "initialSize", initialSize);
        properties.setProperty(PREFIX + "minIdle", minIdle);
        properties.setProperty(PREFIX + "maxActive", maxActive);
        properties.setProperty(PREFIX + "maxWait", maxWait);
        properties.setProperty(PREFIX + "timeBetweenEvictionRunsMillis", timeBetweenEvictionRunsMillis);
        properties.setProperty(PREFIX + "minEvictableIdleTimeMillis", minEvictableIdleTimeMillis);
        properties.setProperty(PREFIX + "validationQuery", validationQuery);
        properties.setProperty(PREFIX + "testWhileIdle", testWhileIdle);
        properties.setProperty(PREFIX + "testOnBorrow", testOnBorrow);
        properties.setProperty(PREFIX + "testOnReturn", testOnReturn);
        properties.setProperty(PREFIX + "filters", filters);
        properties.setProperty(PREFIX + "logSlowSql", logSlowSql);
        properties.setProperty(PREFIX + "poolPreparedStatements", poolPreparedStatements);
        properties.setProperty(PREFIX + "maxPoolPreparedStatementPerConnectionSize", maxPoolPreparedStatementPerConnectionSize);
        properties.setProperty(PREFIX + "useGlobalDataSourceStat", useGlobalDataSourceStat);
        return properties;
    }

    /**
     * 通过connectProperties属性来打开mergeSql功能；慢SQL记录
     *
     * @return
     */
    public Properties getConnectProperties() {
        Properties properties = new Properties();
        properties.setProperty(PREFIX + "stat.mergeSql", mergeSql);
        properties.setProperty(PREFIX + "stat.slowSqlMillis", slowSqlMillis);
        return properties;
    }
}
