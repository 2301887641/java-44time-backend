package com.time.article.admin;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

import java.io.File;
import java.util.List;

/**
 * admin启动类
 *
 * @author suiguozhen on 18/06/28
 */
@SpringBootApplication(scanBasePackages = {"com.time.article"})
@MapperScan("com.time.article.dao.mapper")
public class Application {
    public static void main(String[] args) throws DocumentException {
//        SAXReader saxReader = new SAXReader();
//        Document read = saxReader.read(new File("F:\\java-44time-backend\\time-web\\time-web-admin\\src\\main\\resources\\test.xml"));
//        Element rootElement = read.getRootElement();
//        List<Element> elements = rootElement.elements();
//        System.out.println(rootElement.element("body").element("p"));
        SpringApplication.run(Application.class);
    }
}
