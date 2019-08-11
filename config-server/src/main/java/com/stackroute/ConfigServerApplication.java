package com.stackroute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The server is embeddable in a Spring Boot application, by using the @EnableConfigServer annotation.
 */
@EnableConfigServer
/**
 * Enables Spring Boot auto config and component scanning.
 */
@SpringBootApplication
public class ConfigServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerApplication.class, args);
    }

}
