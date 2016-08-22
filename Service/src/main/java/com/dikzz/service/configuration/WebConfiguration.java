package com.dikzz.service.configuration;

import com.dikzz.service.endpoints.JobEndpoint;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by dikzz on 8/20/16.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackageClasses = JobEndpoint.class)
public class WebConfiguration extends WebMvcConfigurerAdapter {
}
