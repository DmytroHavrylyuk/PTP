package com.dikzz.generator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.jms.*;

/**
 * Created by dikzz on 8/24/16.
 */
@Component
public class JobInitializer {

    private final ConnectionFactory connectionFactory;
    private final String destination;
    private final GeneratorMessageListener generator;
    private Connection connection;

    @Autowired
    public JobInitializer(ConnectionFactory connectionFactory, @Value("${messaging.destination}") String destination, GeneratorMessageListener generator) {
        this.connectionFactory = connectionFactory;
        this.destination = destination;
        this.generator = generator;
    }

    @PostConstruct
    public void init() throws JMSException {
        connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(destination);
        MessageConsumer consumer = session.createConsumer(queue);
        consumer.setMessageListener(generator);
    }

    @PreDestroy
    public void destroy() throws JMSException {
        connection.close();
    }
}
