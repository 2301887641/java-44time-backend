package com.time.article.generator;

import com.time.article.generator.handler.converter.ConverterHandler;
import com.time.article.generator.handler.dao.CriteriaHandler;
import com.time.article.generator.handler.dao.EntityHandler;
import com.time.article.generator.handler.dao.MapperHandler;
import com.time.article.generator.handler.dao.MapperXmlHandler;
import com.time.article.generator.handler.dtocriteria.DtoCriteriaHandler;
import com.time.article.generator.handler.dto.DtoHandler;
import lombok.extern.slf4j.Slf4j;
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
    @Autowired
    private DtoHandler dtoHandler;
    @Autowired
    private DtoCriteriaHandler dtoCriteriaHandler;
    @Autowired
    private ConverterHandler converterHandler;

    @Test
    public void init(){
        //①先生成entity
        entityHandler.generate();
        //②生成criteria
        criteriaHandler.generate();
        //③生成mapper接口
        mapperHandler.generate();
        //④生成mapper xml
        mapperXmlHandler.generate();
        //⑤生成dto
        dtoHandler.generate();
        //⑥生成dtoCriteria
        dtoCriteriaHandler.generate();
        //⑦生成converter
        converterHandler.generate();
        System.out.println("-------------------------生成完毕-----------------------------------");
    }
}
