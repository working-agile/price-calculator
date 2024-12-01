package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

public class DataProcessorTests {


    @Test
    void insert_data_into_database() {

        // database: a-csd
        // user: "prices-admin"
        // password: "prices-secret-password"
        // url = "jdbc:mysql://a-csd-database:3306/prices"

        // table: tr_crs
        // long: "id"
        // string: "tr_date"
        // int: "days"
        // int: "ttl_seats"
        // int: "avail"
        // string: "type"
        // int: "curr"
        // int: full

        // current date: 1. January 2025

        // Item("9 January 2025",   9,  30, 7, "CSPO", 4000, 4000));
        // Item("10 January 2025",  10, 30, 8, "CSD", 3000, 3000));
        // Item("20 January 2025",  20, 30, 27, "CSM", 3000, 3000));

    }



    @Test
    void manual() {

        // 1. Show the current prices
        DataProcessor processor = DataProcessor.getInstance();
        processor.calculateData(false);

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.salesValue);
        System.out.println("-----------------------------------------------------");

        for (Item item: processor.list) {
            System.out.println("Current training courses:");
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
        }

        // 2. Move to next day
        System.out.println("\n\nMove to next day");
        processor.calculateData(true);

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.salesValue);
        System.out.println("-----------------------------------------------------");

        for (Item item: processor.list) {
            System.out.println("Current training courses:");
            System.out.println("------------------------------");
            System.out.println("Training course");
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
        }

    }

}
