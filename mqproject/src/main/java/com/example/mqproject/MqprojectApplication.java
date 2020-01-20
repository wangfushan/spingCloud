package com.example.mqproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MqprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MqprojectApplication.class, args);
    }

}
