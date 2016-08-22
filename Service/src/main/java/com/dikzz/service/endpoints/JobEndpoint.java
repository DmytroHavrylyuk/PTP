package com.dikzz.service.endpoints;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import java.lang.IllegalStateException;

/**
 * Created by dikzz on 8/20/16.
 */
@RestController
@RequestMapping("jobs")
public class JobEndpoint {

    private static final Logger logger = LoggerFactory.getLogger(JobEndpoint.class);

    private final String destination;
    private final ConnectionFactory connectionFactory;

    @Autowired
    public JobEndpoint(ConnectionFactory connectionFactory, @Value("${messaging.destination}") String destination) {
        this.connectionFactory = connectionFactory;
        this.destination = destination;
    }

    @PutMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void put(@RequestBody String data) {
        logger.trace("Send data " + data);
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = session.createQueue(destination);
            TextMessage textMessage = session.createTextMessage(data);
            MessageProducer producer = session.createProducer(queue);
            producer.send(textMessage);
        } catch (JMSException e) {
            logger.error("Cannot put ", e);
            throw new IllegalStateException(e);
        }
    }

}
