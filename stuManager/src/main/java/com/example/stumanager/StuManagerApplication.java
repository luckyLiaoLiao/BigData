package com.example.stumanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan("com.example")
public class StuManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuManagerApplication.class, args);
    }

}
