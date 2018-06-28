package com.time.article.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author suiguozhen on 18/06/28
 */
@SpringBootApplication(scanBasePackages = {"com.time.article"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
