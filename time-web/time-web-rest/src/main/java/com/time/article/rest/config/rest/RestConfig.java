package com.time.article.rest.config.rest;

import com.time.qq.core.QQOauth;
import com.time.wechat.core.WechatOauth;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * rest端统一配置 节省configuration
 * @author suiguozhen on 18/10/30
 */
@Configuration
@EnableSwagger2
public class RestConfig extends WebMvcConfigurerAdapter {

    @Value("${social.qq.appId}")
    private String qqAppId;

    @Value("${social.qq.appSecret}")
    private String qqAppSecret;

    @Value("${social.qq.callbackUrl}")
    private String qqCallbackUrl;

    @Value("${social.wechat.appId}")
    private String wechatAppId;

    @Value("${social.wechat.appSecret}")
    private String wechatAppSecret;

    @Value("${social.wechat.callbackUrl}")
    private String wechatCallbackUrl;

    /**
     * hibernate validator验证配置快速失败
     * @return
     */
    @Bean
    public Validator validator() {
        return Validation.byProvider(HibernateValidator.class)
                .configure()
                .addProperty("hibernate.ValidatorUtils.fail_fast", "true")
                .buildValidatorFactory().getValidator();
    }

    /**
     * 配置qq登陆
     * @return
     */
    @Bean
    public QQOauth qqOauth() {
        return new QQOauth(qqAppId, qqAppSecret, qqCallbackUrl);
    }

    /**
     * 配置微信登陆
     * @return
     */
    @Bean
    public WechatOauth wechatOauth(){
        return new WechatOauth(wechatAppId,wechatAppSecret,wechatCallbackUrl);
    }

    /**
     * 配置swagger
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.time.article"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                /*标题*/
                .title("项目api文档")
                .description("简单优雅的restfull风格")
                .build();
    }

    /**
     * 设置跨域cors
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }
}
