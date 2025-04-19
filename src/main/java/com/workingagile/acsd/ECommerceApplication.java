package com.workingagile.acsd;

import com.workingagile.acsd.backend.DataProcessor;
import com.workingagile.acsd.backend.Item;

import static com.workingagile.acsd.backend.ECommerceBackend.bootingECommerceSystem;

public class ECommerceApplication {

    public static void main(String[] args){

        System.out.println("Booting the system...");
        bootingECommerceSystem();


        System.out.println("Simulating operations...");

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Calculate the current prices...");
        System.out.println("------------------------------------------------------------------");

        DataProcessor processor = DataProcessor.getInstance();
        processor.calculateData(false /* don't change the date */);
        printReport();

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Job signals a new day has started, prices need to be updated...");
        System.out.println("------------------------------------------------------------------");

        processor.calculateData(true /* move to next day */);
        printReport();

    }

    private static void printReport() {
        DataProcessor processor = DataProcessor.getInstance();
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
