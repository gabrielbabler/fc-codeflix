package com.gbabler.admin.catalogue.infrastructure;

import com.gbabler.admin.catalogue.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SpringApplication.run(WebServerConfig.class, args);
    }
}