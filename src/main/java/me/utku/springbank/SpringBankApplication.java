package me.utku.springbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class SpringBankApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBankApplication.class, args);
    }

}