package com.time.article.core.dao.config.druid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 主数据源配置
 *
 * @author suiguozhen on 18/06/30
 */
@Component
@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class MasterDatasourceProperties {
    /**
     * 数据库配置
     */
    private String url;
    private String username;
    private String password;
}
