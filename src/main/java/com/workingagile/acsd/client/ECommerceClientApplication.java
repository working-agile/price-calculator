package com.workingagile.acsd.client;

import com.workingagile.acsd.backend.RefactoredDataProcessor;
import com.workingagile.acsd.backend.domain.Item;

import static com.workingagile.acsd.backend.persistence.ECommercePersistence.bootingECommerceSystem;

public class ECommerceClientApplication {

    public static void main(String[] args){

        System.out.println("Booting the system...");
        bootingECommerceSystem();


        System.out.println("Simulating operations...");

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Calculate the current prices...");
        System.out.println("------------------------------------------------------------------");

        RefactoredDataProcessor processor = RefactoredDataProcessor.getInstance();
        processor.calculateData(false /* don't change the date */);
        printReport();

        System.out.println("------------------------------------------------------------------");
        System.out.println("   Job signals a new day has started, prices need to be updated...");
        System.out.println("------------------------------------------------------------------");

        processor.calculateData(true /* move to next day */);
        printReport();

    }

    private static void printReport() {
        RefactoredDataProcessor processor = RefactoredDataProcessor.getInstance();
        System.out.println("Current prices of scheduled training courses:");
        System.out.println("---------------------------------------------");
        for (Item item : processor.getList()) {
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
            System.out.println();
        }
        System.out.println("Remaining total sales target: " + processor.getSalesValue());
    }


}
