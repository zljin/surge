package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SurgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurgeApplication.class, args);
    }
}
