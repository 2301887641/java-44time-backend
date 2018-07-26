package com.time.article.core.dao.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author suiguozhen on 18/06/30
 */
@Configuration
@EnableConfigurationProperties(DataSourceProperties.class)
public class DruidConfig {
    /**
     * 数据源
     *
     * @param dataSourceProperties
     * @return
     */
    @Bean
    public DataSource druidDataSource(DataSourceProperties dataSourceProperties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(dataSourceProperties.getDruidProperties());
        druidDataSource.setConnectProperties(dataSourceProperties.getConnectProperties());
        return druidDataSource;
    }
}
