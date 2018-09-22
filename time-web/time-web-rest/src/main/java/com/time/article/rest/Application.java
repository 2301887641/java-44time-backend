package com.time.article.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suiguozhen on 18/06/28
 */
@SpringBootApplication(scanBasePackages = {"com.time.article"})
@MapperScan("com.time.article.dao.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
