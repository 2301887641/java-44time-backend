package com.time.article.security.config;

import io.buji.pac4j.filter.CallbackFilter;
import io.buji.pac4j.filter.SecurityFilter;
import io.buji.pac4j.realm.Pac4jRealm;
import io.buji.pac4j.subject.Pac4jSubjectFactory;
import org.apache.shiro.mgt.SubjectFactory;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.AbstractShiroWebFilterConfiguration;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.cas.config.CasProtocol;
import org.pac4j.core.client.Clients;
import org.pac4j.core.config.Config;
import org.pac4j.core.matching.PathMatcher;
import org.pac4j.http.client.direct.ParameterClient;
import org.pac4j.jwt.config.encryption.SecretEncryptionConfiguration;
import org.pac4j.jwt.config.signature.SecretSignatureConfiguration;
import org.pac4j.jwt.credentials.authenticator.JwtAuthenticator;
import org.pac4j.jwt.profile.JwtGenerator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author suiguozhen on 18/07/03
 */
@Configuration
@EnableConfigurationProperties({SecurityProperties.class})
public class SecurityAutoConfiguration extends AbstractShiroWebFilterConfiguration {
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * JWT Token 生成器，对CommonProfile生成然后每次携带token访问
     * @return
     */
    @SuppressWarnings("rawtypes")
    @Bean
    protected JwtGenerator jwtGenerator() {
        return new JwtGenerator(new SecretSignatureConfiguration(securityProperties.getSalt()), new SecretEncryptionConfiguration(securityProperties.getSalt()));
    }

    /**
     * JWT校验器，也就是目前设置的ParameterClient进行的校验器，是rest/或者前后端分离的核心校验器
     * @return
     */
    @Bean
    protected JwtAuthenticator jwtAuthenticator() {
        JwtAuthenticator jwtAuthenticator = new JwtAuthenticator();
        jwtAuthenticator.addSignatureConfiguration(new SecretSignatureConfiguration(securityProperties.getSalt()));
        jwtAuthenticator.addEncryptionConfiguration(new SecretEncryptionConfiguration(securityProperties.getSalt()));
        return jwtAuthenticator;
    }

    /**
     * cas的基本设置，包括或url等等，rest调用协议等
     * @return
     */
    @Bean
    public CasConfiguration casConfiguration() {
        CasConfiguration casConfiguration = new CasConfiguration(securityProperties.getCasLoginUrl());
        casConfiguration.setProtocol(CasProtocol.CAS20);
        casConfiguration.setPrefixUrl(securityProperties.getPrefixUrl());
        return casConfiguration;
    }

    /**
     * 不拦截的路径
     * @return
     */
    @Bean
    public PathMatcher pathMatcher(){
        PathMatcher pathMatcher= new PathMatcher();
        pathMatcher.excludePath("/html/**");
        return pathMatcher;
    }

    /**
     * 自定义pac4jRealm
     * @return
     */
    @Bean(name = "pac4jRealm")
    public Realm pac4jRealm() {
        return new CustomPac4jRealm();
    }

    /**
     * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
     * @return
     */
    @Bean
    protected CasRestFormClient casRestFormClient(CasConfiguration casConfiguration) {
        CasRestFormClient casRestFormClient = new CasRestFormClient();
        casRestFormClient.setConfiguration(casConfiguration);
        casRestFormClient.setName("rest");
        return casRestFormClient;
    }

    /**
     * casClient
     * @return
     */
    @Bean
    public CasClient casClient(CasConfiguration casConfiguration) {
        CasClient casClient = new CasClient();
        casClient.setConfiguration(casConfiguration);
        casClient.setCallbackUrl(securityProperties.getCallbackUrl());
        casClient.setName("cas");
        return casClient;
    }

    /**
     * token校验相关
     * @return
     */
    @Bean
    protected Clients clients(CasClient casClient, CasRestFormClient casRestFormClient) {
        //可以设置默认client
        Clients clients = new Clients();
        //token校验器，可以用HeaderClient更安全
        ParameterClient parameterClient = new ParameterClient("token", jwtAuthenticator());
        parameterClient.setSupportGetRequest(true);
        parameterClient.setName("jwt");
        //支持的client全部设置进去
        clients.setClients(casClient, casRestFormClient, parameterClient);
        return clients;
    }

    @Bean
    protected Config config(Clients clients) {
        Config config = new Config();
        config.setClients(clients);
        return config;
    }

    /**
     * 由于cas代理了用户，所以必须通过cas进行创建对象
     *
     * @return
     */
    @Bean(name = "subjectFactory")
    protected SubjectFactory subjectFactory() {
        return new Pac4jSubjectFactory();
    }

    /**
     * 注册单点登出的listener
     *
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
    public ServletListenerRegistrationBean<?> singleSignOutHttpSessionListener() {
        ServletListenerRegistrationBean bean = new ServletListenerRegistrationBean();
        bean.setListener(new SingleSignOutHttpSessionListener());
        bean.setEnabled(true);
        return bean;
    }

    /**
     * 注册单点登出filter
     *
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public FilterRegistrationBean singleSignOutFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setName("singleSignOutFilter");
        SingleSignOutFilter singleSignOutFilter = new SingleSignOutFilter();
        singleSignOutFilter.setCasServerUrlPrefix(securityProperties.getPrefixUrl());
        singleSignOutFilter.setIgnoreInitConfiguration(true);
        bean.setFilter(singleSignOutFilter);
        bean.addUrlPatterns("/*");
        bean.setEnabled(true);
        return bean;
    }

    /**
     * shiro管理器
     * @param pac4jRealm
     * @param subjectFactory
     * @return
     */
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(Realm pac4jRealm, SubjectFactory subjectFactory) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(pac4jRealm);
        defaultWebSecurityManager.setSubjectFactory(subjectFactory);
        return defaultWebSecurityManager;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new DelegatingFilterProxy("shiroFilter"));
        filterRegistrationBean.addInitParameter("targetFilterLifecycle", "true");
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * 对过滤器进行调整
     *
     * @param securityManager
     * @return
     */
    @Bean({"shiroFilter"})
    @ConditionalOnMissingBean({ShiroFilterFactoryBean.class})
    protected ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager, Config config) {
        ShiroFilterFactoryBean filterFactoryBean = super.shiroFilterFactoryBean();
        filterFactoryBean.setSecurityManager(securityManager);

        //过滤器设置
        Map<String, Filter> filters = new HashMap(16);
        SecurityFilter securityFilter = new SecurityFilter();
        securityFilter.setClients("cas,rest,jwt");
        securityFilter.setConfig(config);
        filters.put("casSecurityFilter", securityFilter);
        CallbackFilter callbackFilter = new CallbackFilter();
        callbackFilter.setConfig(config);
        filters.put("callbackFilter", callbackFilter);
        filterFactoryBean.setFilters(filters);
        return filterFactoryBean;
    }

    /**
     * shiro路径过滤设置
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/callback", "callbackFilter");
        definition.addPathDefinition("/login/**", "callbackFilter");
        definition.addPathDefinition("/captcha/**", "anon");
        definition.addPathDefinition("/**", "casSecurityFilter");
        return definition;
    }

    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }
}
