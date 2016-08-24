package com.dikzz.configuration;

import org.apache.activemq.spring.ActiveMQConnectionFactoryFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by dikzz on 8/21/16.
 */
@Configuration
@ComponentScan("com.dikzz.generator")
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private String appName;

    public ApplicationConfiguration(@Value("${app.name}") String appName) {
        this.appName = appName;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public ActiveMQConnectionFactoryFactoryBean connectionFactory(@Value("${messaging.brokerHost}") String brokerHost) {
        ActiveMQConnectionFactoryFactoryBean connectionFactoryFactoryBean = new ActiveMQConnectionFactoryFactoryBean();
        connectionFactoryFactoryBean.setTcpHostAndPort(brokerHost);
        return connectionFactoryFactoryBean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Application " + appName + " is being started");
    }
}
