package net.ssehub.sparky.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main class which configured spring and starts the eureka discovery service.
 *
 * @author spark
 */
@SpringBootApplication
@EnableEurekaServer
public class Registry {

    /**
     * Starts spring boot application.
     * 
     * @param args java CMD arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Registry.class, args);
    }

}