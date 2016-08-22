package com.dikzz;

import com.dikzz.configuration.ApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by dikzz on 8/21/16.
 */
public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        applicationContext.registerShutdownHook();
        applicationContext.start();
        System.out.println("Hello");
        applicationContext.stop();
    }
}
