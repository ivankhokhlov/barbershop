package ru.omsu.imit.khokhlov.barbershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BarbershopServer {
    public static void main(String[] args) {
        System.out.println("-----");
        SpringApplication.run(BarbershopServer.class,args);
        System.out.println("-----");

    }
}
