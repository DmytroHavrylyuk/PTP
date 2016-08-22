package com.dikzz.service.configuration;

import org.apache.activemq.spring.ActiveMQConnectionFactoryFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dikzz on 8/21/16.
 */
@Configuration
public class DestinationConfiguration {

    private String destination;

    public DestinationConfiguration(@Value("${messaging.destination}") String destination) {
        this.destination = destination;
    }

    @Bean
    public ActiveMQConnectionFactoryFactoryBean connectionFactory(@Value("${messaging.brokerHost}") String brokerHost) {
        ActiveMQConnectionFactoryFactoryBean connectionFactoryFactoryBean = new ActiveMQConnectionFactoryFactoryBean();
        connectionFactoryFactoryBean.setTcpHostAndPort(brokerHost);
        return connectionFactoryFactoryBean;
    }
}
