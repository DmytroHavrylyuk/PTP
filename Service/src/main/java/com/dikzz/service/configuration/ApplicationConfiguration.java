package com.dikzz.service.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by dikzz on 8/20/16.
 */
@Configuration
@Import(DestinationConfiguration.class)
@PropertySource("classpath:application.properties")
public class ApplicationConfiguration implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConfiguration.class);

    private String appName;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySource() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("Application " + appName + " is being started");
    }

    @Value("${app.name}")
    public void setAppName(String appName) {
        this.appName = appName;
    }
}
