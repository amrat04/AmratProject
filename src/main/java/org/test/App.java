package org.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.test.config.AppProperties;

/**
 *  Main App class to start up the Spring Boot Application
 */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}