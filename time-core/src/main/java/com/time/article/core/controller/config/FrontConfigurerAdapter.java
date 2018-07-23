package com.time.article.core.controller.config;

import com.time.article.core.controller.converter.FrontEnumConverterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 前端枚举处理配置
 * @author suiguozhen on 18/07/17
 */
@Configuration
@Lazy
public class FrontConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //  接收枚举转换
        registry.addConverterFactory(frontEnumConverterFactory());
    }

    /**
     * 统一前台枚举转换
     * @return
     */
    @Bean
    public FrontEnumConverterFactory frontEnumConverterFactory(){
        return new FrontEnumConverterFactory();
    }
}
