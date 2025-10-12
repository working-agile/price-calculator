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

            System.out.println("Simulating operations...");

            System.out.println("------------------------------------------------------------------");
            System.out.println("   Calculate the current prices...");
            System.out.println("------------------------------------------------------------------");

            DataProcessor processor = new DataProcessor();
            processor.calculateData(false /* don't change the date */);
            printReport(processor);

            System.out.println("------------------------------------------------------------------");
            System.out.println("   Job signals a new day has started, prices need to be updated...");
            System.out.println("------------------------------------------------------------------");

            processor.calculateData(true /* move to next day */);
            printReport(processor);

        };
    }


    private static void printReport(DataProcessor processor) {
        System.out.println("Current prices of scheduled training courses:");
        System.out.println("---------------------------------------------");
        for (Item item : processor.list) {
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
            System.out.println();
        }
        System.out.println("Remaining total sales target: " + processor.salesValue);
    }

}
