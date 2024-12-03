package com.workingagile.acsd;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataProcessorTests {

    @Test()
    void register_training_courses_in_the_database() throws Exception {

        // database: postgres
        // user: "postgres"
        // password: "postgres"
        // url = "jdbc:postgresql://127.0.0.1:5432/postgres"

        System.out.println("-----------------------------------------------------");
        System.out.println("Setting up the training courses");
        System.out.println("Current date: 1. January 2025");
        System.out.println("Training course CSPO scheduled for January 9th");
        System.out.println("Training course CSD scheduled for January 10th");
        System.out.println("Training course CSM scheduled for January 20th");

        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(url, "postgres", "postgres");
        Statement statement = connection.createStatement();

        statement.executeUpdate("delete from tr_crs");

        String sql1 = "INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price) " +
                "VALUES(1,'9 January 2025',9,30,7,'CSPO',4000)";
        String sql2 = "INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price) " +
                "VALUES(2,'10 January 2025',10,30,8,'CSO',3000)";
        String sql3 = "INSERT INTO tr_crs(id,tr_date,days,ttl_seats,avail,type,full_price) " +
                "VALUES(3, '20 January 2025',20,30,27,'CSM',3000)";

        statement.executeUpdate(sql1);
        statement.executeUpdate(sql2);
        statement.executeUpdate(sql3);

    }



    @Test
    void manual_tests_with_live_database() {

        // 1. Show the current prices
        DataProcessor processor = DataProcessor.getInstance();

        processor.calculateData(false);

        System.out.println("Scheduled training courses:");
        for (Item item: processor.list) {
            System.out.println("------------------------------");
            System.out.println("Type: " + item.type);
            System.out.println("When: " + item.trDate);
            System.out.println("Remaining days before training course: " + item.days);
            System.out.println("Full Price: " + item.full);
            System.out.println("Current price: " + item.curr);
            System.out.println("Number of seats: " + item.seats);
            System.out.println("Remaining available seats: " + item.avail);
        }

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.salesValue);
        System.out.println("-----------------------------------------------------");


        // 2. Move to next day
        System.out.println("\n\nMove to next day\n");
        processor.calculateData(true);

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

        System.out.println("-----------------------------------------------------");
        System.out.println("Total sales target remaining: " + processor.salesValue);
        System.out.println("-----------------------------------------------------");
    }

}
