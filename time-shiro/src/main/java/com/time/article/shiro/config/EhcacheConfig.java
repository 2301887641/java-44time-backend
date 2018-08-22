/*
package com.time.article.shiro.config;

import net.sf.ehcache.CacheManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

*/
/**
 * ehcache配置信息
 * 开启缓存
 * @author suiguozhen on 18/08/21
 *//*

@Configuration
*/
/**开启springboot中的缓存*//*

@EnableCaching
@ConditionalOnProperty(prefix = "profile",value = "ehcache",havingValue = "true")
public class EhcacheConfig {

    */
/**
     * 配置spring整合ehcache  EhCacheCacheManager缓存管理器
     * @param cacheManager cacheManager 是 net.sf.ehcache.CacheManager的一个实例
     * @return
     *//*

    @Bean
    public EhCacheCacheManager ehCacheCacheManager(CacheManager cacheManager){
        return new EhCacheCacheManager(cacheManager);
    }

    */
/**
     * ehcache配置xml
     * @return
     *//*

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
        ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        ehCacheManagerFactoryBean.setShared(true);
        return ehCacheManagerFactoryBean;
    }
}
*/
