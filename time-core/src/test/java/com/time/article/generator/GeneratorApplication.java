package com.time.article.generator;

import com.time.article.generator.handler.CriteriaHandler;
import com.time.article.generator.handler.EntityHandler;
import com.time.article.generator.handler.MapperHandler;
import com.time.article.generator.handler.MapperXmlHandler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 代码生成器
 * @author suiguozhen on 18/08/27
 */
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@Slf4j
public class GeneratorApplication {
    @Autowired
    private EntityHandler entityHandler;
    @Autowired
    private CriteriaHandler criteriaHandler;
    @Autowired
    private MapperHandler mapperHandler;
    @Autowired
    private MapperXmlHandler mapperXmlHandler;

    @Before
    public void init(){
        //①先生成entity
        entityHandler.generate();
        //②生成criteria
        criteriaHandler.generate();
        //③生成mapper接口
        mapperHandler.generate();
        //④生成mapper xml
        mapperXmlHandler.generate();
    }

    @Test
    public void start(){
        System.out.println(111);
    }

}
