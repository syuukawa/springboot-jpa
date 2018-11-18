package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

//定时任务
@EnableScheduling
@SpringBootApplication
@RestController
public class SpringbootJpaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }
}
