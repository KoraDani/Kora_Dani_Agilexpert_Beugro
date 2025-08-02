package com.agilebeugro.beugro;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class BeugroApplication {

    public static void main(String[] args) {
        SpringApplication.run(BeugroApplication.class, args);
    }


    @PostConstruct
    public void onApplicationReady() {
        System.out.println("---------------------------------------------Menu---------------------------------------------\n");
        System.out.println("users \t application \t background \t image");
        System.out.println("----------------------------------------------------------------------------------------------\n");

    }
}
