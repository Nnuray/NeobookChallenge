package com.example.neobookchallenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
//@EntityScan(basePackages = "com.example.entity")

public class NeobookChallengeApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeobookChallengeApplication.class, args);
    }

}
