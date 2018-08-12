package com.time.article.core.dao.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.time.article.core.dao.config.datasource.DynamicDataSource;
import com.time.article.core.message.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * 数据源配置
 * @author suiguozhen on 18/06/30
 */
@Configuration
public class DruidConfig {
    @Autowired
    MasterDatasourceProperties masterDatasourceProperties;

    @Autowired
    SecondaryDatasourceProperties secondaryDatasourceProperties;

    /**
     * 主数据源配置
     * @param druidDatasourceProperties
     * @return
     */
    public DruidDataSource masterDatasource(DruidDatasourceProperties druidDatasourceProperties) {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(druidDatasourceProperties.getDruidProperties(
                masterDatasourceProperties.getUrl(),
                masterDatasourceProperties.getUsername(),
                masterDatasourceProperties.getPassword()
        ));
        druidDataSource.setConnectProperties(druidDatasourceProperties.getConnectProperties());
        return druidDataSource;
    }

    /**
     * 次数据源配置
     * @param druidDatasourceProperties
     * @return
     */
    public DruidDataSource secondaryDatasource(DruidDatasourceProperties druidDatasourceProperties){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.configFromPropety(druidDatasourceProperties.getDruidProperties(
                secondaryDatasourceProperties.getUrl(),
                secondaryDatasourceProperties.getUsername(),
                secondaryDatasourceProperties.getPassword()
        ));
        druidDataSource.setConnectProperties(druidDatasourceProperties.getConnectProperties());
        return druidDataSource;
    }

    /**
     * 单数据源bean
     * @param druidDatasourceProperties
     * @return
     */
    @Bean
    @ConditionalOnProperty(prefix = "profile",name = "dynamicDatasource",havingValue = "false")
    public DruidDataSource singleDatasource(DruidDatasourceProperties druidDatasourceProperties){
        return masterDatasource(druidDatasourceProperties);
    }

    /**
     * 多数据源bean
     * @param druidDatasourceProperties
     * @return
     * @throws SQLException
     */
    @Bean
    @ConditionalOnProperty(prefix = "profile",name = "dynamicDatasource",havingValue = "true")
    public DynamicDataSource dynamicDatasource(DruidDatasourceProperties druidDatasourceProperties) throws SQLException {
        DruidDataSource masterDataSource = masterDatasource(druidDatasourceProperties);
        DruidDataSource secondaryDataSource = secondaryDatasource(druidDatasourceProperties);
        /**初始化数据源*/
        masterDataSource.init();
        secondaryDataSource.init();

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> map = new HashMap<>(2);
        map.put(Constants.MASTER_DATASOURCE_NAME,masterDataSource);
        map.put(Constants.SECONDARY_DATASOURCE_NAME,secondaryDataSource);
        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

}
