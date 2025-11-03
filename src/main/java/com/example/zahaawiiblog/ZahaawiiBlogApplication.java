package com.example.zahaawiiblog;

import com.example.zahaawiiblog.securityFeature.Entity.UserInfo;
import com.example.zahaawiiblog.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ZahaawiiBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZahaawiiBlogApplication.class, args);
    }

    @Bean
    CommandLineRunner loadTestData(UserService userService) {

        return args -> {

            userService.createNewUser(
                    new UserInfo(null, "Zahaawii", "Zahaa@123.dk", "Zahaa123", null, "ROLE_ADMIN", "Zahaawii.jpeg", null)
            );

        };
    }}
