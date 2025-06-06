package ru.payShare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = { "ru.payShare" })
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}