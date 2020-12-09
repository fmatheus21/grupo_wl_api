package com.firecode.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class GrupowlApplication {

    public static void main(String[] args) {
        System.out.println("Senha: " + new BCryptPasswordEncoder().encode("123456"));
        SpringApplication.run(GrupowlApplication.class, args);
    }

}
