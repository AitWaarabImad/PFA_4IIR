package com.example.microbackdiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MicrobackdiscoveryApplication {


    public static void main(String[] args) {
        SpringApplication.run(MicrobackdiscoveryApplication.class, args);
    }

}
