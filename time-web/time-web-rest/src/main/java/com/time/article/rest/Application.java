package com.time.article.rest;

import com.time.utils.core.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Matcher;

/**
 * 启动类
 *
 * @author suiguozhen on 18/06/28
 */
@SpringBootApplication(scanBasePackages = {"com.time.article"})
@MapperScan("com.time.article.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
