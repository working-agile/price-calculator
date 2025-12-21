package com.workingagile.acsd;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import static com.workingagile.acsd.backend.database.DatabaseServer.bootingDatabaseServer;
import static com.workingagile.acsd.backend.mailer.EmailServer.bootingEmailServer;

@SpringBootApplication
public class PriceCalculatorServer {

    public static void main(String[] args) {
        SpringApplication.run(PriceCalculatorServer.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {
            System.out.println("Booting the server...");
            bootingDatabaseServer();
            bootingEmailServer();
            System.out.println("Server booted successfully!");
        };
    }

}
