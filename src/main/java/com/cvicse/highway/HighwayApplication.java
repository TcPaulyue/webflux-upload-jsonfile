package com.cvicse.highway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.cvicse")
public class HighwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HighwayApplication.class, args);
    }

}
