package com.tenetz.tenetz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class TenetzApplication {
    public static void main(String[] args) {
        SpringApplication.run(TenetzApplication.class, args);
    }

}
