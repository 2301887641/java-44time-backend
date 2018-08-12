package com.time.article.core.dao.config.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 动态数据源配置
 * @author suiguozhen on 18/06/30
 */
@ConfigurationProperties(prefix = "dynamicDatasource.secondaryConfig")
@Getter
@Setter
@Component
public class SecondaryDatasourceProperties {
    /**
     * 数据库配置
     */
    private  String url;
    private  String username;
    private  String password;
}
