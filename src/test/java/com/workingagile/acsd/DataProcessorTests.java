package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DataProcessorTests {

    @Test
    void manual() {

        // Refactor so that I can execute a test with
        // the new functionalities
        //
        // - add new functionality at a big method: calculateData()
        //
        // but the method is static

        /*
        List items = new ArrayList<Item>();
        items.add(new Item(1,"10 January 2025",10, 30, 8, "CSD", 3000, 3000));
        items.add(new Item(2,"9 January 2025",9, 30, 7, "CSPO", 4000, 4000));
        items.add(new Item(3, "20 January 2025", 20, 30, 27, "CSM", 3000, 3000));

        TestableDataProcessor dataProcessor = new TestableDataProcessor();

        List<Item> updatedItems = dataProcessor.calculateData(items, false);

        System.out.println("Current training courses:");

        for (Item trainingCourse: updatedItems) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + trainingCourse.type);
            System.out.println("When: " + trainingCourse.trDate);
            System.out.println("Remaining days before training course: " + trainingCourse.days);
            System.out.println("Full Price: " + trainingCourse.full);
            System.out.println("Current price: " + trainingCourse.curr);
            System.out.println("Number of seats: " + trainingCourse.seats);
            System.out.println("Remaining available seats: " + trainingCourse.avail);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.salesValue);
        System.out.println("-----------------------------------------------------");

        System.out.println("\n\nMove to next day");

        DataProcessor.calculateData(true);

        System.out.println("Updated training courses...");

        for (Item trainingCourse: updatedItems) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + trainingCourse.type);
            System.out.println("When: " + trainingCourse.trDate);
            System.out.println("Remaining days before training course: " + trainingCourse.days);
            System.out.println("Full Price: " + trainingCourse.full);
            System.out.println("Current price: " + trainingCourse.curr);
            System.out.println("Number of seats: " + trainingCourse.seats);
            System.out.println("Remaining available seats: " + trainingCourse.avail);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.salesValue);
        System.out.println("-----------------------------------------------------");

        */
    }

}
