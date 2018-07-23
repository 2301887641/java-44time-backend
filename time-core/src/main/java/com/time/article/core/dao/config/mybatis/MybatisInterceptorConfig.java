package com.time.article.core.dao.config.mybatis;

        import com.time.article.core.dao.plugin.TreePlugin;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

/**
 * 注册tree插件
 * @author suiguozhen on 18/07/20
 */
@Configuration
public class MybatisInterceptorConfig {
    @Bean
    public TreePlugin sqlStatsInterceptor(){
        TreePlugin treePlugin = new TreePlugin();
        return treePlugin;
    }
}
