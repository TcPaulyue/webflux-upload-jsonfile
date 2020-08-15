package com.cvicse.highway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class HighwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighwayApplication.class, args);
    }

}
