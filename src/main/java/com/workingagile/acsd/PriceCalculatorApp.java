package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;


import static com.workingagile.acsd.backend.persistence.ECommercePersistence.bootingECommercePersistence;

@SpringBootApplication
public class PriceCalculatorApp {

    public static void main(String[] args) {
        SpringApplication.run(PriceCalculatorApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Booting the system...");

            bootingECommercePersistence();

            System.out.println("------------------------------------------------------------------");
            System.out.println("   Calculate the current prices...");
            System.out.println("------------------------------------------------------------------");

            DataProcessor.calculateData();

            System.out.println("------------------------------------------------------------------");
            System.out.println("   List training courses and current prices");
            System.out.println("------------------------------------------------------------------");

            printTrainingCourses();

            System.out.println("------------------------------------------------------------------");
            System.out.println("   List sales target");
            System.out.println("------------------------------------------------------------------");

            printSalesTarget();
        };
    }


    private static void printTrainingCourses() {
        for (Item item : DataProcessor.list) {
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
            System.out.println();
        }
    }

    private static void printSalesTarget() {
        System.out.println("Remaining total sales target: " + DataProcessor.salesValue);
    }


}
