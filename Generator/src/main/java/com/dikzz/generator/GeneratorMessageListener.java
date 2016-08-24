package com.dikzz.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.concurrent.TimeUnit;

/**
 * Created by dikzz on 8/24/16.
 */
@Component
public class GeneratorMessageListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(GeneratorMessageListener.class);

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage) message;
            logger.trace("Thread " + Thread.currentThread().getName() + " started to process message " + textMessage.getText());
            TimeUnit.SECONDS.sleep(30);
            logger.trace("Thread " + Thread.currentThread().getName() + " stopped to process message " + textMessage.getText());
        } catch (JMSException | InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
