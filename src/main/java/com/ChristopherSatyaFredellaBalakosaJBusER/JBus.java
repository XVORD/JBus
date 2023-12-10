package com.ChristopherSatyaFredellaBalakosaJBusER;
import com.ChristopherSatyaFredellaBalakosaJBusER.dbjson.JsonDBEngine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * JBus Class
 * Main class for the package which launches SpringBoot
 * @author Christopher Satya
 */
@SpringBootApplication
public class JBus {
    /**
     * Main method which launches SpringBoot and Json Database
     * @param args Arguments when JBus is run
     */
    public static void main(String[] args) {
        JsonDBEngine.Run(JBus.class);
        SpringApplication.run(JBus.class, args);
        Runtime.getRuntime().addShutdownHook(new Thread(()-> JsonDBEngine.join()));
    }
}