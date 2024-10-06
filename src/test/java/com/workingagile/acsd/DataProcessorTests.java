package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

public class DataProcessorTests {

    @Test
    void manual() {

        System.out.println("Today is: 1. January 2025");
        System.out.println("Registering training courses:");

        Item i1 = new Item("10 January 2025",10, 30, 10, true, "CSD", 3000);
        Item i2 = new Item("9 January 2025",9, 30, 9, true, "CSPO", 4000);
        Item i3 = new Item("20 January 2025", 20, 30, 20, false, "CSM", 3000);
        Item[] list = new Item[] {i1, i2, i3};

        DataProcessor.list = list;

        DataProcessor.calculateData(false);

        for (int i = 0; i < list.length; i++) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + list[i].type);
            System.out.println("When: " + list[i].date);
            System.out.println("Remaining days before training course: " + list[i].days);
            System.out.println("Online: " + list[i].online);
            System.out.println("Full Price: " + list[i].full);
            System.out.println("Current price: " + list[i].current);
            System.out.println("Number of seats: " + list[i].seats);
            System.out.println("Remaining available seats: " + list[i].avail);
        }
        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.value);
        System.out.println("-----------------------------------------------------");

        System.out.println("\n\nMove to next day: 2. January");

        DataProcessor.calculateData(true);

        System.out.println("Registered training courses");

        for (int i = 0; i < list.length; i++) {
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + list[i].type);
            System.out.println("When: " + list[i].date);
            System.out.println("Remaining days before training course: " + list[i].days);
            System.out.println("Online: " + list[i].online);
            System.out.println("Full Price: " + list[i].full);
            System.out.println("Current price: " + list[i].current);
            System.out.println("Number of seats: " + list[i].seats);
            System.out.println("Remaining available seats: " + list[i].avail);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + DataProcessor.value);
        System.out.println("-----------------------------------------------------");
    }

}
